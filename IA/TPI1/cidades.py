
import math

from tree_search import *


def func_actions(connections, city):
    actlist = []
    for (C1, C2, D) in connections:
        if (C1 == city):
            actlist += [(C1, C2)]
        elif (C2 == city):
            actlist += [(C2, C1)]
    return actlist


def func_result(city, action):
    (C1, C2) = action
    if C1 == city:
        return C2


def func_cost(connections, city, action):
    if action[0] != city:
        return None
    for (c1, c2, d) in connections:
        if (c1 in action and c2 in action):
            return d
    return None


def func_heuristic(coordinates, city, goal_city):
    (x1, y1) = coordinates[city]
    (x2, y2) = coordinates[goal_city]
    return math.hypot(x1-x2, y1-y2)


def func_satisfies(city, goal_city):
    return goal_city == city

# search domain


class Cidades(SearchDomain):
    def __init__(self, connections, coordinates):
        self.connections = connections
        self.coordinates = coordinates

    def actions(self, city):
        return func_actions(self.connections, city)

    def result(self, city, action):
        return func_result(city, action)

    def cost(self, city, action):
        return func_cost(self.connections, city, action)

    def heuristic(self, city, goal_city):
        return func_heuristic(self.coordinates, city, goal_city)

    def satisfies(self, city, goal_city):
        return func_satisfies(city, goal_city)
