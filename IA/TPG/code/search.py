# Authors: Marco Almeida (103440), Rui Machado (65081)
from __future__ import annotations
from abc import ABC, abstractmethod
from common_plus import Map,Coordinates
from typing import Tuple, List

HEURISTIC_STRATS = ['greedy','a*']
COST_STRATS = ['uniform','a*']

class SearchDomain(ABC):

    @abstractmethod
    def __init__(self):
        pass

    @abstractmethod
    def actions(self, state):
        pass

    @abstractmethod
    def result(self, state, action):
        pass

    @abstractmethod
    def cost(self, state, action):
        pass

    @abstractmethod
    def heuristic(self, state, goal_state):
        pass


class SearchProblem:
    def __init__(self, domain):
        self.domain = domain
        self.initial = domain.initial_map

    def goal_test(self, mapa: Map):
        return self.domain.satisfies(mapa)

class SearchNode:
    def __init__(self, state: Map, parent, depth, cost, heuristic=0, action=None, cursor=Coordinates(0,0)):
        self.state = state
        self.parent = parent
        self.action = action
        self.cursor = cursor
        self.depth = depth
        self.cost = cost
        self.heuristic = heuristic

    def __str__(self):
        return f"node({self.state}, {self.parent}, {self.depth}, {self.cost})"

    def __repr__(self):
        return str(self)

class SearchTree:
    def __init__(self, problem: SearchProblem, strategy='breadth', cursor=Coordinates(0,0)):
        self.problem = problem
        root = SearchNode(problem.initial, None, 0, 0, cursor)
        self.open_nodes: list[SearchNode] = [root]
        self.strategy = strategy
        self.cost = 0
        self.terminals = 0
        self.non_terminals = 0

    def display_plays(self, node: SearchNode) -> List[Tuple[str,str]]:
        aux = node
        plays = []
        while (aux.parent != None):
            plays.insert(0, aux.action)
            plays.insert(1,aux.cost)
            aux = aux.parent
        return plays

    def get_plays(self, node):
        aux = node
        plays = []
        while (aux.parent != None):
            plays.insert(0,(aux.action[0],aux.state.piece_coordinates(aux.action[0])))
            aux = aux.parent
        return plays


    def search(self) -> List[Tuple[str, str]]:
        known_grids = set()  # Set de todas as grids exploradas(nos vários ramos)
        while self.open_nodes != []: 
            node: SearchNode = self.open_nodes.pop(0)
            known_grids.add(node.state.grid_txt)

            if self.problem.goal_test(node.state):  # se ja chegamos ao goal
                self.terminals = len(self.open_nodes) + 1
                return node

            self.non_terminals += 1
            lnewnodes: list[SearchNode] = []
            for action in self.problem.domain.actions(node.state):
                newstate_grid: str = self.problem.domain.result(node.state, action)

                if newstate_grid not in known_grids:
                    newstate = Map(str(node.state.pieces) + " " + newstate_grid + " " + str(node.state.pieces))
                    new_cursor = 0

                    # Cost Related #########################
                    if self.strategy in COST_STRATS:
                        old_coords = node.state.get_car_extremes(action[0])
                        old_orientation = node.state.get_car_orientation(action[0])
                        old_top_left = old_coords[0]

                        new_coords = newstate.get_car_extremes(action[0])
                        new_top_left = new_coords[0]
                        new_bottom_right = new_coords[1]

                        if node.cursor.x <= old_top_left.x and old_orientation == "horizontal" or node.cursor.y <= old_top_left.y and old_orientation == "vertical":
                            new_cursor = new_top_left
                            cost_index = 0
                        else:
                            new_cursor = new_bottom_right
                            cost_index = -1
                    #########################################
                    if self.strategy in HEURISTIC_STRATS:
                        if self.problem.domain.initial_map.grid_size > 6:
                            heuristic = self.problem.domain.heuristic3(newstate)
                        else:
                            heuristic = self.problem.domain.heuristic(newstate)
                    else:
                        heuristic = 0
                    
                    cost = node.cost + self.problem.domain.cost2(node,action[0],cost_index) if self.strategy in COST_STRATS else 0
                    newnode = SearchNode(newstate, node, node.depth+1, cost, heuristic, action, new_cursor)
                    lnewnodes.append(newnode)
                    known_grids.add(newstate.grid_txt)

            self.add_to_open(lnewnodes)
        return None

    # juntar novos nos a lista de nos abertos de acordo com a estrategia
    def add_to_open(self, lnewnodes):
        if self.strategy == 'breadth':
            self.open_nodes.extend(lnewnodes)

        elif self.strategy == 'depth':
            self.open_nodes[:0] = lnewnodes

        elif self.strategy == 'uniform':
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.cost)

        elif self.strategy == 'greedy':
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.heuristic)

        elif self.strategy == 'a*':
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.heuristic + node.cost)
