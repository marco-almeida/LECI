"""Common data structures. Can be used by any module.
Modified from the original common.py in the original code.
Authors: Marco Almeida (103440), Rui Machado (65081)
"""
from __future__ import annotations

import math
from dataclasses import dataclass


@dataclass
class Coordinates:
    x: int
    y: int


class MapException(Exception):
    """Exception Moving Pieces."""


class Map:
    """Representation of a map."""

    empty_tile = "o"
    wall_tile = "x"
    player_car = "A"

    # type: ignore
    def __init__(self, txt: str, play: tuple[str, str] = None):
        """Initialize Map from string."""
        pieces, grid, movements = txt.split(" ")
        self.pieces = int(pieces)
        self.movements = int(movements)
        self.grid_size = int(math.sqrt(len(grid)))
        self.grid_txt = grid

    def __eq__(self, other: Map) -> bool:
        if not isinstance(other, Map):
            return NotImplemented  # don't attempt to compare against unrelated types
        return other.grid_txt == self.grid_txt

    # Representation of ocupied map positions through tuples x,y,value.
    @property
    def coordinates(self):
        return [(index % self.grid_size, math.floor(index / self.grid_size), letter) for index, letter in enumerate(self.grid_txt) if letter != self.empty_tile]

    def get(self, cursor: Coordinates):  # Return piece at cursor position.
        if 0 <= cursor.x < self.grid_size and 0 <= cursor.y < self.grid_size:
            return self.grid_txt[cursor.y*self.grid_size+cursor.x]
        return "x"  # MapException("Out of the grid")"

    # List coordinates holding a piece.
    def piece_coordinates(self, piece: str):
        return [self.get_coordinates_by_grid_txt_index(index) for index, letter in enumerate(self.grid_txt) if letter == piece]

    ########################### auxiliary methods ################################
    def move_v2(self, piece: str, direction: Coordinates):
        '''Move piece in direction given by a vector'''

        if piece == self.wall_tile:
            return 'error'

        piece_coord = self.piece_coordinates(piece)

        # Don't move vertical pieces sideways
        if direction.x != 0 and self.get_car_orientation(piece) == 'vertical':
            return 'error'
        # Don't move horizontal pieces up-down
        if direction.y != 0 and self.get_car_orientation(piece) == 'horizontal':
            return 'error'

        def sum(a: Coordinates, b: Coordinates):
            return Coordinates(a.x + b.x, a.y + b.y)

        for pos in piece_coord:
            if not self.get(sum(pos, direction)) in [piece, self.empty_tile]:
                return 'error'

        self.new_grid_txt = self.grid_txt
        for pos in piece_coord:
            self.new_grid_txt = self.new_grid_txt[:(pos.y*self.grid_size+pos.x)] + self.empty_tile + self.new_grid_txt[(
                (pos.y*self.grid_size+pos.x)+1):]  # custa alguma eficiencia mas é worth para nao se usar o repr

        for pos in piece_coord:
            new_pos = sum(pos, direction)
            self.new_grid_txt = self.new_grid_txt[:(
                new_pos.y*self.grid_size+new_pos.x)] + piece + self.new_grid_txt[((new_pos.y*self.grid_size+new_pos.x)+1):]
        return self.new_grid_txt

    def amount_cars_blocking_cars_blocking_A(self) -> int:
        ''' pegar em todos que tao a bloquear o vermelho(verticais) e ver se nos seus extremos +1/-1 estao a ser bloqueados'''
        if not self.red_is_blocked():
            return 0
        cars_blocking_a = self.cars_blocking_A()
        amount = 0
        for car in cars_blocking_a:
            if len(self.actions_car(car)) > 0:
                amount += 1

        return amount

    def cars_blocking_A(self) -> list[str]:
        if not self.red_is_blocked():
            return []

        cars = []
        next_index = self.grid_txt.rindex('A') + 1
        while next_index % self.grid_size != 0:
            if self.grid_txt[next_index] != 'o':
                cars.append(self.grid_txt[next_index])
            next_index += 1
        return cars

    def red_is_blocked(self):  # nao otimizado mas nao importa muito
        # get grid_txt, ver o ultimo index de A e ver se ha obstaculos ate ao fim da linha
        next_index = self.grid_txt.rindex('A') + 1
        while next_index % self.grid_size != 0:
            if self.grid_txt[next_index] != 'o':
                return True
            next_index += 1
        return False

    def actions_car(self, piece: str) -> list[tuple[str, str]]:
        '''Returns list of actions available for piece'''
        action_list: list[tuple[str, str]] = []
        orientation: str = self.get_car_orientation(piece)

        if orientation == 'horizontal':
            # ver esquerda
            left_index: int = self.grid_txt.find(piece)
            if left_index % self.grid_size != 0:  # Se left_index nao está no extremo esquerdo do mapa
                left_friend_index = left_index - 1
                left_friend = self.grid_txt[left_friend_index]
                if left_friend == 'o':
                    action_list.append((piece, 'a'))

            # ver direita
            right_index: int = self.grid_txt.rfind(piece)
            if (right_index + 1) % self.grid_size != 0:  # Se right_index nao está no extremo direito do mapa
                right_friend_index = right_index + 1
                right_friend = self.grid_txt[right_friend_index]
                if right_friend == 'o':
                    # Damos prioridade às açoes que movem peças para a direita (Depth/Breadth)
                    action_list.insert(0, (piece, 'd'))

        else:  # orientation == 'vertical'
            # ver cima
            up_index: int = self.grid_txt.find(piece)
            if up_index - self.grid_size >= 0:  # Se up_index nao está no extremo superior (devia ser >0)
                up_friend_index = up_index - self.grid_size
                up_friend = self.grid_txt[up_friend_index]
                if up_friend == 'o':
                    action_list.append((piece, 'w'))

            # ver baixo
            down_index: int = self.grid_txt.rfind(piece)
            if down_index + self.grid_size < len(self.grid_txt):  # Se down_index nao está no extremo inferior
                down_friend_index = down_index + self.grid_size
                down_friend = self.grid_txt[down_friend_index]
                if down_friend == 'o':
                    action_list.append((piece, 's'))
        return action_list

    def get_car_extremes(self, piece: str) -> list[Coordinates]:
        '''A car has at least 2 coordinates, this returns the extremes (top/bottom or right/left)'''
        first_index: int = self.grid_txt.find(piece)
        last_index: int = self.grid_txt.rfind(piece)
        first = self.get_coordinates_by_grid_txt_index(first_index)
        second = self.get_coordinates_by_grid_txt_index(last_index)
        return [first, second]

    def get_car_orientation(self, piece: str) -> str:
        '''Returns "horizontal" if car has two coordinates with equal y  else "vertical"'''
        first_index: int = self.grid_txt.find(piece)
        if self.grid_txt[first_index + 1] == piece:  # Carros horizontais aparecem na grid_txt com os caracteres todos juntos
            return "horizontal"
        return "vertical"

    def get_coordinates_by_grid_txt_index(self, index: int):
        return Coordinates(index % self.grid_size, math.floor(index / self.grid_size))

    def test_win(self) -> bool:
        if self.red_is_blocked():
            return False
        return True
