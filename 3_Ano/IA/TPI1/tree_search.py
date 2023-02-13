
# Module: tree_search
#
# This module provides a set o classes for automated
# problem solving through tree search:
#    SearchDomain  - problem domains
#    SearchProblem - concrete problems to be solved
#    SearchNode    - search tree nodes
#    SearchTree    - search tree with the necessary methods for searhing
#
#  (c) Luis Seabra Lopes
#  Introducao a Inteligencia Artificial, 2012-2019,
#  Inteligência Artificial, 2014-2019

from abc import ABC, abstractmethod

# Dominios de pesquisa
# Permitem calcular
# as accoes possiveis em cada estado, etc


class SearchDomain(ABC):

    # construtor
    @abstractmethod
    def __init__(self):
        pass

    # lista de accoes possiveis num estado
    @abstractmethod
    def actions(self, state):
        pass

    # resultado de uma accao num estado, ou seja, o estado seguinte
    @abstractmethod
    def result(self, state, action):
        pass

    # custo de uma accao num estado
    @abstractmethod
    def cost(self, state, action):
        pass

    # custo estimado de chegar de um estado a outro
    @abstractmethod
    def heuristic(self, state, goal):
        pass

    # test if the given "goal" is satisfied in "state"
    @abstractmethod
    def satisfies(self, state, goal):
        pass


# Problemas concretos a resolver
# dentro de um determinado dominio
class SearchProblem:
    def __init__(self, domain, initial, goal):
        self.domain = domain
        self.initial = initial
        self.goal = goal

    def goal_test(self, state):
        return self.domain.satisfies(state, self.goal)

# Nos de uma arvore de pesquisa


class SearchNode:
    def __init__(self, state, parent):
        self.state = state
        self.parent = parent

    def __str__(self):
        return "no(" + str(self.state) + "," + str(self.parent) + ")"

    def __repr__(self):
        return str(self)

# Arvores de pesquisa


class SearchTree:

    # construtor
    def __init__(self, problem, strategy='breadth'):
        self.problem = problem
        if isinstance(problem, SearchProblem):
            root = SearchNode(problem.initial, None)
            self.all_nodes = [root]
            self.open_nodes = [0]
        self.strategy = strategy
        self.solution = None
        self.non_terminals = 0

    # obter o caminho (sequencia de estados) da raiz ate um no
    def get_path(self, node):
        if node.parent == None:
            return [node.state]
        path = self.get_path(self.all_nodes[node.parent])
        path += [node.state]
        return(path)

    # procurar a solucao
    def search(self):
        while self.open_nodes != []:
            nodeID = self.open_nodes.pop(0)
            node = self.all_nodes[nodeID]
            if self.problem.goal_test(node.state):
                self.solution = node
                self.terminals = len(self.open_nodes)+1
                return self.get_path(node)
            lnewnodes = []
            self.non_terminals += 1
            for a in self.problem.domain.actions(node.state):
                newstate = self.problem.domain.result(node.state, a)
                if newstate not in self.get_path(node):
                    newnode = SearchNode(newstate, nodeID)
                    lnewnodes.append(len(self.all_nodes))
                    self.all_nodes.append(newnode)
            self.add_to_open(lnewnodes)
        return None

    # juntar novos nos a lista de nos abertos de acordo com a estrategia
    def add_to_open(self, lnewnodes):
        if self.strategy == 'breadth':
            self.open_nodes.extend(lnewnodes)
        elif self.strategy == 'depth':
            self.open_nodes[:0] = lnewnodes
        elif self.strategy in ['A*', 'IBA*']:
            self.astar_add_to_open(lnewnodes)
