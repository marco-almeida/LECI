
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
        from cidades import Cidades # ex 8, tem que ser aqui, otherwise dá circular import error
        return Cidades.cost(self, state,action)

    # custo estimado de chegar de um estado a outro
    @abstractmethod
    def heuristic(self, state, goal):
        from cidades import Cidades # ex 8, tem que ser aqui, otherwise dá circular import error
        return Cidades.heuristic(self, state,goal)

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
        return self.domain.satisfies(state,self.goal)

# Nos de uma arvore de pesquisa
class SearchNode:
    def __init__(self,state,parent, heuristic = 0): # ver se ha outra forma em vez de estar a modificar construtor
        self.state = state
        self.parent = parent
        self.depth = 0 if parent == None else parent.depth + 1 # ex 2
        self.cost = 0 if parent == None else parent.cost + SearchDomain.cost(self, self.state, (self.state, self.parent.state)) # ex 8
        self.heuristic = heuristic # ex 12
    def __str__(self):
        return "no(" + str(self.state) + "," + str(self.depth) + "," + str(self.parent)  +")"
    def __repr__(self):
        return str(self)

# Arvores de pesquisa
class SearchTree:

    # construtor
    def __init__(self,problem, strategy='breadth'): 
        self.problem = problem
        root = SearchNode(problem.initial, None)
        self.open_nodes = [root]
        self.strategy = strategy
        self.solution = None
      
    @property # ex 3
    def length(self):
        return self.solution.depth

    @property # ex 6
    def avg_branching(self):
        return (self.terminals + self.non_terminals - 1) / self.non_terminals

    @property # ex 9
    def cost(self):
        return self.solution.cost

    # obter o caminho (sequencia de estados) da raiz ate um no
    def get_path(self,node):
        if node.parent == None:
            return [node.state]
        path = self.get_path(node.parent)
        path += [node.state]
        return(path)

    # procurar a solucao
    def search(self, limit = None):
        self.non_terminals = 0 # ex 5
        while self.open_nodes != []: # open_nodes sao sitios que faltam ser visitados?
            node = self.open_nodes.pop(0)
            if self.problem.goal_test(node.state): # se ja chegamos ao goal
                self.solution = node
                self.terminals = len(self.open_nodes) + 1 # ex 5
                return self.get_path(node)
            self.non_terminals += 1 # ex 5
            if limit == None or node.depth < limit: # ex 4
                lnewnodes = [] 
                for a in self.problem.domain.actions(node.state): # por cada açao possivel neste node
                    newstate = self.problem.domain.result(node.state,a) # ver o resultado de cada açao possivel
                    if newstate not in self.get_path(node): # ex 1 -- se cidade for nova...
                        newnode = SearchNode(newstate,node, SearchDomain.heuristic(self, newstate, self.problem.goal)) # ex 12 -- add 3o argumento no construtor
                        lnewnodes.append(newnode)
                self.add_to_open(lnewnodes)
        return None

    # juntar novos nos a lista de nos abertos de acordo com a estrategia
    def add_to_open(self,lnewnodes):
        if self.strategy == 'breadth':
            self.open_nodes.extend(lnewnodes)
        elif self.strategy == 'depth':
            self.open_nodes[:0] = lnewnodes
        elif self.strategy == 'uniform' or self.strategy == 'greedy': # ex 10 / 13
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key = lambda x : x.cost) # porque tem que se ver o de menor custo sempre

