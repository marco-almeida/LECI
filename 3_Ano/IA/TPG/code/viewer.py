"""Viewer application."""
from __future__ import annotations

import argparse
import asyncio
import json
import logging
import os
import random

import pygame
import requests
import websockets

from common import Coordinates, Map

logging.basicConfig(level=logging.DEBUG)
logger_websockets = logging.getLogger("websockets")
logger_websockets.setLevel(logging.WARN)

logger = logging.getLogger("Viewer")
logger.setLevel(logging.DEBUG)

VIEW_HIGHSCORES = 5

BLOCK_SIDE = 30
BLOCK_SIZE = BLOCK_SIDE, BLOCK_SIDE

COLORS = {
    "background": (50, 50, 50),
    "black": (0, 0, 0),
    "white": (255, 255, 255),
    "red": (255, 0, 0),
    "pink": (255, 105, 180),
    "blue": (135, 206, 235),
    "orange": (255, 165, 0),
    "yellow": (255, 255, 0),
    "grey": (120, 120, 120),
    "green": (0, 240, 0),
}

COLOR_MAP = {
    "cursor": COLORS["black"],
    "cursor_selected": COLORS["white"],
    "background": (50, 50, 50),
    "info": COLORS["white"],
    "info_title": (255, 165, 0),
    "x": COLORS["pink"],
    "A": COLORS["red"],  # Our car is always the red one
    "B": (0xC0, 0xC0, 0xC0),
    "C": (0x2F, 0x4F, 0x4F),
    "D": (0x2E, 0x8B, 0x57),
    "E": (0x7F, 0x0, 0x0),
    "F": (0x80, 0x80, 0x0),
    "G": (0x00, 0x0, 0x80),
    "H": (0xFF, 0x8C, 0x0),
    "I": (0xFF, 0xD7, 0x0),
    "J": (0x7C, 0xFC, 0x0),
    "K": (0x0, 0xFA, 0x9A),
    "L": (0x41, 0x69, 0xE1),
}


async def messages_handler(websocket_path, queue):
    """Handle server side messages, putting them into a queue."""
    async with websockets.connect(websocket_path) as websocket:
        await websocket.send(json.dumps({"cmd": "join"}))

        while True:
            update = await websocket.recv()
            queue.put_nowait(update)


def scale(pos):
    """Scale positions according to gfx."""
    x, y = pos
    return int(x * BLOCK_SIDE / SCALE), int(y * BLOCK_SIDE / SCALE)


def draw_info(surface, text, pos, color=(0, 0, 0), background=None):
    """Create text based surfaces for information display."""
    myfont = pygame.font.Font(None, int(24 / SCALE))
    textsurface = myfont.render(text, True, color, background)

    x, y = pos
    if x > surface.get_width():
        pos = surface.get_width() - (textsurface.get_width() + 10), y
    if y > surface.get_height():
        pos = x, surface.get_height() - textsurface.get_height()

    if background:
        surface.blit(background, pos)
    else:
        erase = pygame.Surface(textsurface.get_size())
        erase.fill(COLOR_MAP["background"])

    surface.blit(textsurface, pos)
    return textsurface.get_width(), textsurface.get_height()


async def main_loop(queue):
    """Process events from server and display's."""
    win = pygame.display.set_mode((480 // SCALE, 320 // SCALE))
    pygame.display.set_caption("Rush Hour - WAITING FOR GAME TO START")

    draw_info(win, "Waiting for server to start a new game", (0, win.get_height() / 8), COLORS["white"])
    pygame.display.update()

    logging.info("Waiting for map information from server")
    state = await queue.get()  # first state message includes map information
    logging.debug("Initial game status: %s", state)
    newgame_json = json.loads(state)
    player_name = ""

    win.fill((0, 0, 0))

    def draw_blocks(game_size, grid, cursor, selected, x_offset=0, y_offset=0):
        pygame.draw.rect(
            win,
            COLOR_MAP["background"],
            (
                x_offset * game_size.x * BLOCK_SIDE / SCALE,
                y_offset * game_size.y * BLOCK_SIDE / SCALE,
                game_size.x * BLOCK_SIDE / SCALE,
                game_size.y * BLOCK_SIDE / SCALE,
            ),
            0,
        )

        for x, y, piece in grid.coordinates:

            if piece not in COLOR_MAP:
                COLOR_MAP[piece] = [
                    random.randint(90, 255),
                    random.randint(90, 255),
                    random.randint(90, 255),
                ]

            if piece == "x":
                pygame.draw.circle(
                    win,
                    COLOR_MAP[piece],
                    (
                        (x + x_offset) * BLOCK_SIDE / SCALE + BLOCK_SIDE / SCALE / 2,
                        (y + y_offset) * BLOCK_SIDE / SCALE + BLOCK_SIDE / SCALE / 2,
                    ),
                    BLOCK_SIDE / SCALE / 2 - 2,
                    0,
                )
            else:
                pygame.draw.rect(
                    win,
                    COLOR_MAP[piece],
                    (
                        (x + x_offset) * BLOCK_SIDE / SCALE,
                        (y + y_offset) * BLOCK_SIDE / SCALE,
                        BLOCK_SIDE / SCALE,
                        BLOCK_SIDE / SCALE,
                    ),
                    0,
                )

        cur = Coordinates(*cursor)
        pygame.draw.rect(
            win,
            COLOR_MAP["cursor_selected"] if selected else COLOR_MAP["cursor"],
            (
                (cur.x + x_offset) * BLOCK_SIDE / SCALE,
                (cur.y + y_offset) * BLOCK_SIDE / SCALE,
                BLOCK_SIDE / SCALE,
                BLOCK_SIDE / SCALE,
            ),
            4,
        )

    dimensions = Coordinates(*newgame_json["dimensions"])

    grid = Map(newgame_json["grid"])

    draw_blocks(dimensions, grid, newgame_json["cursor"], False)

    game_speed = newgame_json["game_speed"]
    pygame.display.set_caption("Rush Hour")

    while True:
        pygame.display.update()

        for event in pygame.event.get():
            if event.type == pygame.QUIT or event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE:
                pygame.quit()
                return

        try:
            state = json.loads(queue.get_nowait())
            if "level" in state:
                level = state["level"]
            if "score" in state:
                score = state["score"]
            if "player" in state:
                player_name = state["player"]

            if "game_speed" in state:
                game_speed = state["game_speed"]

            win.fill((0, 0, 0))

            if "highscores" in state:
                logging.debug("Final game status: %s", state)

                if GLOBAL_HIGHSCORES:
                    highscores = [
                        [highscore["player"], highscore["score"]]
                        for highscore in requests.get(GLOBAL_HIGHSCORES).json()
                    ]
                    state["highscores"].extend(highscores)
                    state["highscores"].sort(key=lambda h: h[1], reverse=True)
                    state["highscores"] = state["highscores"][:9]
                    state["highscores"].append([player_name, score])
                    state["highscores"].sort(key=lambda h: h[1], reverse=True)

                draw_info(
                    win,
                    "HIGHSCORES",
                    (win.get_width() / 4, win.get_height() / 8),
                    COLOR_MAP["info_title"],
                )
                for idx, [name, sc] in enumerate(state["highscores"]):
                    if idx >= VIEW_HIGHSCORES:
                        break

                    draw_info(
                        win,
                        f"{sc:>05}    {name:<24}",
                        (
                            win.get_width() / 4,
                            win.get_height() / 8 + BLOCK_SIDE * (1 + idx),
                        ),
                        COLOR_MAP["info_title"]
                        if [player_name, score] == [name, sc]
                        else COLOR_MAP["info"],
                    )
                continue

            logger.info(state)
            grid = Map(state["grid"])
            dimensions = Coordinates(*state["dimensions"])
            draw_blocks(dimensions, grid, state["cursor"], state["selected"] != "")

            cursor = Coordinates(*state["cursor"])

            information = [
                (f"LEVEL: {level}", 1, COLOR_MAP["info"]),
                (f"SCORE: {score}", 2, COLOR_MAP["info"]),
                (f"PIECE: {grid.get(cursor)}", 3, COLOR_MAP["info"]),
            ]

            for txt, line, color in information:
                draw_info(
                    win,
                    txt,
                    scale((dimensions.x + 1, line)),
                    color,
                )

        except asyncio.queues.QueueEmpty:
            await asyncio.sleep(1.0 / game_speed)
            continue


if __name__ == "__main__":
    SERVER = os.environ.get("SERVER", "localhost")
    PORT = os.environ.get("PORT", "8000")

    parser = argparse.ArgumentParser()
    parser.add_argument("--server", help="IP address of the server", default=SERVER)
    parser.add_argument(
        "--scale", help="reduce size of window by x times", type=int, default=1
    )
    parser.add_argument("--port", help="TCP port", type=int, default=PORT)
    parser.add_argument(
        "--global-highscores",
        help="Retrieve global highscores",
        default=False,
        action="store_true",
    )
    parser.add_argument(
        "--grading-server",
        help="url of grading server",
        default="http://atnog-tetriscores.av.it.pt/highscores",
    )
    arguments = parser.parse_args()
    SCALE = arguments.scale
    GLOBAL_HIGHSCORES = (
        arguments.grading_server if arguments.global_highscores else None
    )

    pygame.font.init()

    async def main():
        """Start viewer tasks."""
        q = asyncio.Queue()
        PROGRAM_ICON = pygame.image.load("data/icon.jpeg")
        pygame.display.set_icon(PROGRAM_ICON)

        ws_path = f"ws://{arguments.server}:{arguments.port}/viewer"

        await asyncio.gather(messages_handler(ws_path, q), main_loop(q))

    try:
        asyncio.run(main())
    except RuntimeError as err:
        logger.error(err)
