"""Rush Hour Game Logic."""
from __future__ import annotations

import asyncio
import logging
import random
from typing import Dict

from common import Coordinates, Map, MapException

logger = logging.getLogger("Game")
logger.setLevel(logging.DEBUG)

GAME_SPEED = 10
CRAZY_STEP = 10

LEVEL: Dict[int, Map] = {}


class Game:
    """Main Class."""

    def __init__(self, x: int = 8, y: int = 8) -> None:
        """Initialize Game."""
        logger.info("Game")

        self.levels = {}
        with open("levels.txt", "r") as f:
            for lvl, map_str in enumerate(f.readlines(), start=1):
                map = Map(map_str.strip())
                self.levels[map.pieces] = map

        self.dimensions = Coordinates(x, y)
        self.grid = None

        self.game_speed = GAME_SPEED
        self._running = True
        self.cursor = Coordinates(self.dimensions.x // 2, self.dimensions.y // 2)

        self.level = 0
        self._score = 0
        self._total_steps = 0
        self._step = 0

        self.next_level()

    @property
    def running(self):
        """Status on game."""
        return self._running

    @property
    def score(self):
        """Return Current Score."""
        return self._score - self._step

    def stop(self):
        """Stop the game."""
        if self._step:
            logger.info("GAME OVER at step %s - score %s", self._step, self.score)
        self._running = False

    def next_level(self):
        """Update all state variables to a new level."""
        # Score points from previous map
        self._score = self.score + (self.grid.movements * 2 if self.grid else 0)

        self.level += 1
        try:
            self.grid = self.levels[self.level]
            logger.info("NEXT LEVEL: %s", self.level)
            self.dimensions = Coordinates(self.grid.grid_size, self.grid.grid_size)
            self.cursor = Coordinates(self.dimensions.x // 2, self.dimensions.y // 2)
            
        except KeyError:
            logger.info("No more levels... You WIN!")
            self.stop()
            return

        self._total_steps += self._step
        self._step = 0
        self._timeout = self.grid.movements + GAME_SPEED * 60

        self._lastkeypress = "-"
        self._selected = None

    def info(self):
        """Return game state information."""
        return {
            "dimensions": (self.dimensions.x, self.dimensions.y),
            "level": self.level,
            "grid": str(self.grid),
            "score": self.score,
            "game_speed": self.game_speed,
            "cursor": (self.cursor.x, self.cursor.y),
            "selected": self._selected if self._selected else "",
        }

    def keypress(self, key: str):
        """Update locally last key pressed."""
        self._lastkeypress = key

    async def loop(self):
        """Run Main Game Loop."""
        if self._step % 100 == 0:
            logger.info("Loop %s - score: %s", self._step, self.score)

        await asyncio.sleep(1.0 / GAME_SPEED)

        self._step += 1
        if self._step >= self._timeout:
            self.stop()

        if self._step % CRAZY_STEP == 0:
            try:
                _, _, random_piece = random.choice(self.grid.coordinates)
                random_direction = random.choice([Coordinates(0,-1), Coordinates(0, 1), Coordinates(-1, 0), Coordinates(1, 0)])
                self.grid.move(random_piece, random_direction)
                logger.debug("Crazy driver: %s moved %s", random_piece, random_direction)
                if random_piece == self._selected:
                    self.cursor.x += random_direction.x
                    self.cursor.y += random_direction.y
            except MapException:
                pass

        if self._lastkeypress == " ":  # Toggle
            if self._selected is None:
                logger.debug("Select %s", self.grid.get(self.cursor))
                self._selected = self.grid.get(self.cursor)
                if self._selected in [self.grid.wall_tile, self.grid.empty_tile]:
                    logger.debug("Can't move %s", self._selected)
                    self._selected = None
            else:
                logger.debug("UnSelect")
                self._selected = None

        elif self._lastkeypress in "wasd":
            if self._selected:
                try:
                    if self._lastkeypress == "w" and self.cursor.y > 0:
                        self.grid.move(self._selected, Coordinates(0, -1))
                        self.cursor.y -= 1
                    elif self._lastkeypress == "a" and self.cursor.x > 0:
                        self.grid.move(self._selected, Coordinates(-1, 0))
                        self.cursor.x -= 1
                    elif (
                        self._lastkeypress == "s"
                        and self.cursor.y + 1 < self.dimensions.y
                    ):
                        self.grid.move(self._selected, Coordinates(0, 1))
                        self.cursor.y += 1
                    elif (
                        self._lastkeypress == "d"
                        and self.cursor.x + 1 < self.dimensions.x
                    ):
                        self.grid.move(self._selected, Coordinates(1, 0))
                        self.cursor.x += 1
                except MapException as exc:
                    logger.error("Can't move %s: %s", self._selected, exc)
            else:
                if self._lastkeypress == "w" and self.cursor.y > 0:
                    self.cursor.y -= 1
                elif self._lastkeypress == "a" and self.cursor.x > 0:
                    self.cursor.x -= 1
                elif (
                    self._lastkeypress == "s" and self.cursor.y + 1 < self.dimensions.y
                ):
                    self.cursor.y += 1
                elif (
                    self._lastkeypress == "d" and self.cursor.x + 1 < self.dimensions.x
                ):
                    self.cursor.x += 1

        # Test victory:
        if self.grid.test_win():
            logger.info("Level %s COMPLETED", self.level)
            self.next_level()

        self._lastkeypress = "-"

        return self.info()
