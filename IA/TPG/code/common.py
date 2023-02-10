"""Common data structures. Can be used by any module."""
from __future__ import annotations

import math
from dataclasses import dataclass


@dataclass
class Coordinates:
    """Coordinates data class."""

    x: int
    y: int


class MapException(Exception):
    """Exception Moving Pieces."""


class Map:
    """Representation of a map."""

    empty_tile = "o"
    wall_tile = "x"
    player_car = "A"

    def __init__(self, txt: str):
        """Initialize Map from string."""
        pieces, grid, movements = txt.split(" ")
        self.pieces = int(pieces)
        self.movements = int(movements)
        self.grid_size = int(math.sqrt(len(grid)))
        self.grid = []

        line = []
        for i, pos in enumerate(grid):
            line.append(pos)
            if (i + 1) % self.grid_size == 0:
                self.grid.append(line)
                line = []

    def __repr__(self):
        """Revert map object to string."""
        raw = ""
        for line in self.grid:
            for column in line:
                raw += column
        return f"{self.pieces} {raw} {self.movements}"

    @property
    def coordinates(self):
        """Representation of ocupied map positions through tuples x,y,value."""
        _coordinates = []

        for y, line in enumerate(self.grid):
            for x, column in enumerate(line):
                if column != self.empty_tile:
                    _coordinates.append((x, y, column))

        return _coordinates

    def get(self, cursor: Coordinates):
        """Return piece at cursor position."""
        if 0 <= cursor.x < self.grid_size and 0 <= cursor.y < self.grid_size:
            return self.grid[int(cursor.y)][int(cursor.x)]
        raise MapException("Out of the grid")

    def piece_coordinates(self, piece: str):
        """List coordinates holding a piece."""
        return [Coordinates(x, y) for (x, y, p) in self.coordinates if p == piece]

    def move(self, piece: str, direction: Coordinates):
        """Move piece in direction fiven by a vector."""
        if piece == self.wall_tile:
            raise MapException("Blocked piece")

        piece_coord = self.piece_coordinates(piece)

        # Don't move vertical pieces sideways
        if direction.x != 0 and any([line.count(piece) == 1 for line in self.grid]):
            raise MapException("Can't move sideways")
        # Don't move horizontal pieces up-down
        if direction.y != 0 and any([line.count(piece) > 1 for line in self.grid]):
            raise MapException("Can't move up-down")

        def sum(a: Coordinates, b: Coordinates):
            return Coordinates(a.x + b.x, a.y + b.y)

        for pos in piece_coord:
            if not self.get(sum(pos, direction)) in [piece, self.empty_tile]:
                raise MapException("Blocked piece")

        for pos in piece_coord:
            self.grid[pos.y][pos.x] = self.empty_tile

        for pos in piece_coord:
            new_pos = sum(pos, direction)
            self.grid[new_pos.y][new_pos.x] = piece

    def test_win(self):
        """Test if player_car has crossed the left most column."""
        return any(
            [c.x == self.grid_size - 1 for c in self.piece_coordinates(self.player_car)]
        )


""" TODO move to tests
m = Map("02 ooooBoooooBoAAooBooooooooooooooooooo 14")
print(m)
print(m.get(Dimensions(4,0)))
assert m.move("A", Coordinates(1, 0))
assert m.move("A", Coordinates(-1, 0))
assert not m.move("A", Coordinates(0, 1))
assert not m.move("A", Coordinates(0, -1))
assert m.move("B", Coordinates(0, 1))
assert m.move("B", Coordinates(0, -1))
assert not m.move("B", Coordinates(1,0))
assert not m.move("B", Coordinates(-1,0))
print(m)
"""
