# Authors: Marco Almeida (103440), Rui Machado (65081)
from common_plus import Coordinates, Map
from search import SearchDomain, SearchNode, SearchProblem, SearchTree
from typing import List, Tuple
DIRECTION_DICT = {'a': Coordinates(-1, 0), 'd': Coordinates(1, 0), 's': Coordinates(0, 1), 'w': Coordinates(0, -1)}

class Dominio(SearchDomain):
    def __init__(self, mapa: Map):
        self.initial_map = mapa

    def actions(self, mapa: Map) -> List[Tuple[str, str]]:
        ''' todas as açoes de todos os carros '''
        visited_cars: list[str] = []
        all_actions: list[tuple[str, str]] = []

        for x, y, piece in mapa.coordinates:  # mapa.coordinates mais eficiente que pegar em apenas cada objeto
            if piece not in visited_cars and piece != 'x':
                for action in mapa.actions_car(piece):
                    all_actions.append(action)
                visited_cars.append(piece)
        return all_actions

    def result(self, mapa: Map, action: Tuple[str, str]) -> str:
        ''' Returns new grid_txt after action is taken.'''
        piece: str = action[0]
        jogada: str = action[1]
        new_grid_txt = mapa.move_v2(piece, DIRECTION_DICT[jogada])

        return new_grid_txt

    #####################################################################################
    def cost(self, node: SearchNode) -> int: #
        return node.state.amount_cars_blocking_cars_blocking_A() + len(node.state.cars_blocking_A())

    def cost2(self, node: SearchNode, piece: str, index: int) -> int: # Retorna o custo do movimento do cursor
        old_position = node.state.get_car_extremes(piece)[index]
        return abs(node.cursor.x - old_position.x) + abs(node.cursor.y - old_position.y) + 1

    def cost3(self, node: SearchNode) -> int: # Dá prioridade a movimentos consecutivos com a mesma peça
        if node.parent != None:
            if node.parent.action != None:
                return 0 if node.action[0] == node.parent.action[0] else 1 
        return 0

    ############################################################################################
    def heuristic(self, state: Map) -> int: # Distancia do carro ao Goal
        if not state.red_is_blocked():
            return 0
        list_coordinates = state.get_car_extremes("A")
        return state.grid_size - list_coordinates[1].x

    def heuristic2(self, state: Map) -> int: # Quantidade de carros a bloquear o carro A
        return len(state.cars_blocking_A())

    def heuristic3(self, state: Map) -> int: # Quantidade de carros a bloquear A + carros a bloquear os bloqueios de A
        return state.amount_cars_blocking_cars_blocking_A() + self.heuristic2(state)

    #########################################
    def satisfies(self, mapa: Map) -> bool:
        return mapa.test_win()

##########################################################################
def search_path(strategy: str, grid: str, cursor: Coordinates) -> List[Tuple[str, str]]:
    mapa = Map(grid)
    dominio = Dominio(mapa)
    my_prob = SearchProblem(dominio)
    my_tree = SearchTree(my_prob,strategy,cursor)
    my_tree.strategy = strategy
    solution_node = my_tree.search()
    return my_tree.get_plays(solution_node)
