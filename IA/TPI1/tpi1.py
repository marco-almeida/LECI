# Aluno: Marco António Alves Almeida 103440
# Assignment discussed with Rui Machado 65081

from tree_search import *
from cidades import *
from blocksworld import *

DOMAIN = 0
DOMAIN_ACTIONS = 0
DOMAIN_RESULT = 1
DOMAIN_COST = 2
DOMAIN_HEURISTIC = 3
DOMAIN_SATISFIES = 4
DOMAIN_BRANCHING = 5
INITIAL_STATE = 1
GOAL_STATE = 2
NODE_STATE = 0
NODE_PARENT = 1
NODE_COST = 2
NODE_HEURISTIC = 3
NODE_DEPTH = 4


def func_branching(connections, coordinates):  # Ex. 1
    '''Returns the branching factor of the search space.'''
    c = Cidades(connections, coordinates)
    visited = set()
    available_nodes = 0

    for (city1, city2, cost) in connections:
        if city1 not in visited:
            available_nodes += len(c.actions(city1))
        if city2 not in visited:
            available_nodes += len(c.actions(city2))
        visited.add(city1)
        visited.add(city2)

    return available_nodes / (len(visited)) - 1


class MyCities(Cidades):
    def __init__(self, connections, coordinates):
        super().__init__(connections, coordinates)

        self.branching_estimate = func_branching(connections, coordinates)

class MySTRIPS(STRIPS):
    def __init__(self, optimize=False):
        super().__init__(optimize)

    def simulate_plan(self, state, plan):
        for action in plan:
            state = self.result(state, action)
        return state


class MyNode(SearchNode):
    def __init__(self, state, parent, cost=0, heuristic=0, depth=0):
        super().__init__(state, parent)
        self.cost = cost
        self.heuristic = heuristic
        self.depth = depth


class MyTree(SearchTree):

    def __init__(self, problem, strategy='breadth', optimize=0, keep=0.25):
        self.optimize = optimize
        self.keep = keep
        self.problem = problem
        self.strategy = strategy
        self.solution = None
        self.open_nodes = [0]
        self.non_terminals = 0
        self.all_node_depth = []
        self.avg_node_depth = 0
        self.nodes_fechados = []

        problem_initial = problem.initial if optimize < 2 else problem[INITIAL_STATE]
        root = MyNode(problem_initial, None) if optimize == 0 else (
            problem_initial, None, 0, 0, 0)
        self.all_nodes = [root]

    def get_cost(self, node, state, action):
        if self.optimize == 0:
            return node.cost + self.problem.domain.cost(
                state, action)
        elif self.optimize == 1:
            return node[NODE_COST] + self.problem.domain.cost(
                state, action)
        elif self.optimize >= 2:
            return node[NODE_COST] + self.problem[DOMAIN][DOMAIN_COST](
                state, action)

    def get_heuristic(self, newstate):
        return self.problem.domain.heuristic(
            newstate, self.problem.goal) if self.optimize < 2 else self.problem[DOMAIN][DOMAIN_HEURISTIC](newstate, self.problem[GOAL_STATE])

    def get_depth(self, node):
        return node.depth + 1 if self.optimize == 0 else node[NODE_DEPTH] + 1

    def get_state(self, node_state, a):
        return self.problem.domain.result(node_state, a) if not isinstance(
            self.problem, tuple) else self.problem[DOMAIN][DOMAIN_RESULT](node_state, a)

    def get_actions(self, node_state):
        return self.problem.domain.actions(node_state) if not isinstance(
            self.problem, tuple) else self.problem[DOMAIN][DOMAIN_ACTIONS](node_state)

    def get_satisfies(self, node_state):
        return self.problem.goal_test(node_state) if not isinstance(self.problem, tuple) else self.problem[DOMAIN][DOMAIN_SATISFIES](node_state, self.problem[GOAL_STATE])

    def get_branching_estimate(self):
        return self.problem[DOMAIN][DOMAIN_BRANCHING] if not isinstance(
            self.problem, SearchProblem) else self.problem.domain.branching_estimate

    def astar_add_to_open(self, lnewnodes):
        if not lnewnodes:
            return
        self.open_nodes.extend(lnewnodes)
        if isinstance(self.all_nodes[lnewnodes[0]], MyNode):
            self.open_nodes.sort(
                key=lambda x: self.all_nodes[x].heuristic + self.all_nodes[x].cost)
        else:
            self.open_nodes.sort(
                key=lambda x: self.all_nodes[x][NODE_HEURISTIC] + self.all_nodes[x][NODE_COST])

        if self.strategy == 'IBA*':
            self.forget_worst_terminals()

    def forget_worst_terminals(self):
        d = self.avg_node_depth
        ramification_factor = self.get_branching_estimate()

        max_nodes_given_depth = ramification_factor ** d
        num_keep = math.trunc(
            self.keep * max_nodes_given_depth) + 1

        if isinstance(self.all_nodes[0], MyNode):
            def key(
                x): return self.all_nodes[x].heuristic + self.all_nodes[x].cost
        else:
            def key(
                x): return self.all_nodes[x][NODE_HEURISTIC] + self.all_nodes[x][NODE_COST]
        self.open_nodes = sorted(self.open_nodes, key=key)[:num_keep]

    # obter o caminho (sequencia de estados) da raiz ate um no
    def get_path(self, node):
        node_parent = node.parent if isinstance(
            node, MyNode) else node[NODE_PARENT]
        node_state = node.state if isinstance(
            node, MyNode) else node[NODE_STATE]
        if node_parent == None:
            return [node_state]
        path = self.get_path(self.all_nodes[node_parent])
        path += [node_state]
        return(path)

    def on_best_path(self, newnode):
        for id in self.open_nodes:
            node = self.all_nodes[id]
            if node[NODE_STATE] == newnode[NODE_STATE] and newnode[NODE_COST] < node[NODE_COST]:
                self.all_nodes[id] = newnode
                self.add_to_open([id])
                self.non_terminals -= 1
                return True
        return False

    def search2(self):
        while self.open_nodes != []:
            node_id = self.open_nodes.pop(0)
            node = self.all_nodes[node_id]
            node_state = node.state if self.optimize == 0 else node[NODE_STATE]
            node_depth = node.depth if self.optimize == 0 else node[NODE_DEPTH]
            self.nodes_fechados.append(node_id)

            if self.get_satisfies(node_state):
                self.solution = node
                self.terminals = len(self.open_nodes)+1
                return self.get_path(node)

            lnewnodes = []
            self.non_terminals += 1

            if self.optimize == 4:
                cv_state_vizinhos_sucessores_de_n = [self.get_state(
                    node_state, x) for x in self.get_actions(node_state) if x not in self.get_path(node)]

                open_nodes_states = []
                for num in self.open_nodes:
                    open_nodes_states.append(
                        self.all_nodes[num][NODE_STATE])

                closed_nodes_states = []

                for num in self.nodes_fechados:
                    closed_nodes_states.append(
                        self.all_nodes[num][NODE_STATE])

                for new_state in cv_state_vizinhos_sucessores_de_n:
                    newnode = (new_state, node_id, self.get_cost(node,
                                                                 node_state, (node_state, new_state)),
                               self.get_heuristic(new_state), self.get_depth(node))

                    if new_state not in closed_nodes_states and new_state not in open_nodes_states:
                        lnewnodes.append(len(self.all_nodes))
                        self.all_nodes.append(newnode)
                        self.all_node_depth.append(node[NODE_DEPTH])
                        self.avg_node_depth = sum(
                            self.all_node_depth)/len(self.all_node_depth)

                    if new_state in closed_nodes_states or new_state in open_nodes_states:
                        self.on_best_path(newnode)
                        self.all_node_depth.append(node[NODE_DEPTH])
                        self.avg_node_depth = sum(
                            self.all_node_depth)/len(self.all_node_depth)
            else:

                for a in self.get_actions(node_state):
                    newstate = self.get_state(node_state, a)

                    if newstate not in self.get_path(node):
                        heuristic = self.get_heuristic(newstate)
                        depth = self.get_depth(node)
                        cost = self.get_cost(node, node_state, a)

                        newnode = MyNode(newstate, node_id, cost, heuristic, depth) if self.optimize == 0 else (
                            newstate, node_id, cost, heuristic, depth)

                        self.all_node_depth.append(node_depth)
                        lnewnodes.append(len(self.all_nodes))

                        self.all_nodes.append(newnode)
                        self.avg_node_depth = sum(
                            self.all_node_depth)/len(self.all_node_depth)

            self.add_to_open(lnewnodes)
        return None

# If needed, auxiliary functions can be added
