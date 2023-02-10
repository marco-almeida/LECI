"""Network Game Server."""
from __future__ import annotations

import argparse
import asyncio
import json
import logging
import os.path
import random
from collections import namedtuple
from typing import Any, Dict, Set

import requests
import websockets
from requests import RequestException
from websockets.legacy.protocol import WebSocketCommonProtocol

from game import Game

logging.basicConfig(
    level=logging.DEBUG, format="%(asctime)s - %(name)s - %(levelname)s - %(message)s"
)
wslogger = logging.getLogger("websockets")
wslogger.setLevel(logging.WARN)

logger = logging.getLogger("Server")
logger.setLevel(logging.INFO)

Player = namedtuple("Player", ["name", "ws"])

HIGHSCORE_FILE = "highscores.json"
MAX_HIGHSCORES = 10


class GameServer:
    """Network Game Server."""

    def __init__(self, level: int, timeout: int, seed: int = 0, grading: bool = None):
        """Initialize Gameserver."""
        self.seed = seed
        self.game = Game()
        self.players: asyncio.Queue[Player] = asyncio.Queue()
        self.viewers: Set[WebSocketCommonProtocol] = set()
        self.current_player: Player | None = None
        self.grading = grading
        self._level = level  # game level
        self._timeout = timeout  # timeout for game

        self._highscores = []
        if os.path.isfile(HIGHSCORE_FILE):
            with open(HIGHSCORE_FILE, "r") as infile:
                self._highscores = json.load(infile)

    def save_highscores(self, score: int):
        """Update highscores, storing to file."""
        if not self.current_player:
            raise Exception("Can't save highscores without current player")

        logger.debug("Save highscores")
        logger.info(
            "%s FINAL SCORE <%s>",
            self.current_player.name,
            score,
        )

        self._highscores.append((self.current_player.name, score))
        self._highscores = sorted(self._highscores, key=lambda s: s[1], reverse=True)[
            :MAX_HIGHSCORES
        ]


        with open(HIGHSCORE_FILE, "w") as outfile:
            json.dump(self._highscores, outfile)

    async def send_info(self, game_info: Dict[str, Any], highscores: bool = False):
        """Send game info to viewer and player."""
        if not self.current_player:
            raise Exception("Will not send information if there isn't any current user")

        if highscores:
            game_info["highscores"] = self._highscores
            game_info["player"] = self.current_player.name

        for viewer in self.viewers:
            try:
                await viewer.send(json.dumps(game_info))
            except Exception:
                self.viewers.remove(viewer)
                break

        await self.current_player.ws.send(json.dumps(game_info))

    async def incomming_handler(self, websocket: WebSocketCommonProtocol, path: str):
        """Process new clients arriving at the server."""
        try:
            async for message in websocket:
                data = json.loads(message)
                if "cmd" not in data:
                    continue
                if data["cmd"] == "join":
                    if path == "/player":
                        logger.info("<%s> has joined", data["name"])
                        await self.players.put(Player(data["name"], websocket))

                    if path == "/viewer":
                        logger.info("Viewer connected")
                        self.viewers.add(websocket)

                if (
                    data["cmd"] == "key"
                    and self.current_player
                    and self.current_player.ws == websocket
                ):
                    logger.debug((self.current_player.name, data))
                    if len(data["key"]) > 0:
                        self.game.keypress(data["key"][0])
                    else:
                        self.game.keypress("")

        except websockets.exceptions.ConnectionClosed as closed_reason:
            logger.info("Client disconnected: %s", closed_reason)
            if websocket in self.viewers:
                self.viewers.remove(websocket)

    async def mainloop(self):
        """Run the game."""
        while True:
            logger.info("Waiting for player")
            self.current_player = await self.players.get()

            if self.current_player.ws.closed:
                logger.error("<%s> disconnect while waiting", self.current_player.name)
                continue

            try:
                logger.info("Starting game for <%s>", self.current_player.name)
                if self.seed > 0:
                    random.seed(self.seed)

                self.game = Game()

                game_info = await self.game.loop()
                await self.send_info(game_info)

                if self.grading:
                    game_record = {}
                    game_record["player"] = self.current_player.name

                while self.game.running:
                    state = await self.game.loop()
                    state["player"] = self.current_player.name

                    state = json.dumps(state)

                    await self.current_player.ws.send(state)

                    for viewer in self.viewers:
                        try:
                            await viewer.send(state)
                        except Exception:
                            self.viewers.remove(viewer)
                            break

                self.save_highscores(self.game.score)

                game_info = self.game.info()
                game_info["player"] = self.current_player.name

                await self.send_info(game_info, highscores=True)
                await self.current_player.ws.close()
                self.current_player = None

            except websockets.exceptions.ConnectionClosed:
                self.current_player = None
            finally:
                try:
                    if self.grading:
                        game_record["score"] = self.game.score
                        requests.post(self.grading, json=game_record, timeout=2)
                except RequestException as err:
                    logger.error(err)
                    logger.warning("Could not save score to server")

                if self.current_player:
                    logger.info("Disconnecting <%s>", self.current_player.name)
                    await self.current_player.ws.close()


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--bind", help="IP address to bind to", default="")
    parser.add_argument("--port", help="TCP port", type=int, default=8000)
    parser.add_argument("--seed", help="Seed number", type=int, default=0)
    parser.add_argument(
        "--grading-server",
        help="url of grading server",
        default="http://atnog-tetriscores.av.it.pt/game",
    )
    args = parser.parse_args()

    async def main():
        """Start server tasks."""
        g = GameServer(0, -1, args.seed, args.grading_server)

        game_loop_task = asyncio.ensure_future(g.mainloop())

        logger.info("Listenning @ %s:%s", args.bind, args.port)
        websocket_server = websockets.serve(g.incomming_handler, args.bind, args.port)

        await asyncio.gather(websocket_server, game_loop_task)

    asyncio.run(main())
