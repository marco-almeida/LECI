# Authors: Marco Almeida (103440), Rui Machado (65081)
from __future__ import annotations
from common_plus import Map
from domain import *
import time
import sys

STRATEGIES = [ "depth", "breadth", "uniform","greedy", "a*"]

def main():

    strategy = "depth"
    lvl = "0"
    if strategy not in STRATEGIES:
        print("Strategy not defined")
        print("Available strategies: depth, breadth, uniform, greedy, a*")
        exit(1)


    ###############################################################################3

    if lvl == "0":
        total_moves = 0
        total_time = 0
        total_time_search = 0
        total_cost = 0
        ultimo_nivel = 57
        for i in range(1, ultimo_nivel+1):
            start = time.perf_counter()
            numeroJogadas = len(test_search(strategy, levels()[i-1]))
            end = time.perf_counter()
            searchTime = round(end - start,1)
            cost = get_cost(strategy, levels()[i-1])
            #terminals,non_terminals = get_terminals(strategy,levels()[i])
            print(f"Level {i} - {strategy} searchTime: {searchTime} moves: {numeroJogadas} cost:{cost}")
            #print(f"Level {i} - {strategy} executionTime: {numeroJogadas * 0.1}")
            #print(f"Level {i} - {strategy} totalTime: {(searchTime) + (numeroJogadas * 0.1)}")
            #print(f"Level {i} - {strategy} terminals: {terminals}")
            #print(f"Level {i} - {strategy} non terminals: {non_terminals}")
            total_time_search += searchTime
            total_time += (searchTime + (numeroJogadas * 0.1))
            total_moves += numeroJogadas
            total_cost += cost
            cost_time = total_cost * 0.1


        
        minutos = int(total_time//60)
        segundos = round(total_time % 60, 2)
        print(f"Total searchtime {strategy}: {round(total_time_search,1)}")
        print(f"Total time {strategy}: {minutos}:{segundos}")
        print(f"Total steps {strategy}: {total_moves}")
        print(f"Total cost {strategy}: {total_cost}")
        minutos2 = int(cost_time/60)
        segundos2 = round(cost_time % 60, 2)
        print(f"Time expected {strategy}: {minutos2}:{segundos2}")
    
    else:
        """
        print(f"Level {int(lvl)} ###################")
        start = time.perf_counter()
        solution = test_search("depth",levels()[int(lvl)-1])
        end = time.perf_counter()
        numeroJogadas = len(solution)
        searchTime = round(end - start,1)
        terminals,non_terminals = get_terminals("depth",levels()[int(lvl)-1])
        print(f"Depth:")
        print(f"Time of Search: {searchTime} moves: {numeroJogadas}")
        print(f"Time of execut: {round(numeroJogadas * 0.1, 1)}")
        print(f"Total time: {(searchTime) + numeroJogadas * 0.1}")
        print(f"T: {terminals} Non-T: {non_terminals}")
        print(solution)
        print("\n")
        """
        
        start = time.perf_counter()
        solution = test_search("breadth",levels()[int(lvl)-1])
        end = time.perf_counter()
        numeroJogadas = len(solution)
        searchTime = round(end - start,1)
        terminals,non_terminals = get_terminals("breadth",levels()[int(lvl)-1])
        total_cost = get_cost("breadth",levels()[int(lvl)-1])
        print(f"Breadth:")
        print(f"Time of Search: {searchTime} moves: {numeroJogadas}")
        print(f"Time of execut: {round(numeroJogadas * 0.1, 1)}")
        print(f"Total time: {(searchTime) + numeroJogadas * 0.1}")
        print(f"Total cost: {total_cost}")
        #print(f"T: {terminals} Non-T: {non_terminals}")
        print(solution)
        print("\n")
        
        
        start = time.perf_counter()
        solution = test_search("uniform",levels()[int(lvl)-1])
        end = time.perf_counter()
        numeroJogadas = len(solution)
        searchTime = round(end - start,1)
        terminals,non_terminals = get_terminals("uniform",levels()[int(lvl)-1])
        total_cost = get_cost("uniform",levels()[int(lvl)-1])
        print(f"Uniform:")
        print(f"Time of Search: {searchTime} moves: {numeroJogadas}")
        print(f"Time of execut: {round(numeroJogadas * 0.1, 1)}")
        print(f"Total time: {(searchTime) + numeroJogadas * 0.1}")
        print(f"Total cost: {total_cost}")
        #print(f"T: {terminals} Non-T: {non_terminals}")
        print(solution)
        print("\n")

        """
        start = time.perf_counter()
        solution = test_search("greedy",levels()[int(lvl)-1])
        end = time.perf_counter()
        numeroJogadas = len(solution)
        searchTime = round(end - start,1)
        terminals,non_terminals = get_terminals("greedy",levels()[int(lvl)-1])
        print(f"Greedy:")
        print(f"Time of Search: {searchTime} moves: {numeroJogadas}")
        print(f"Time of execut: {round(numeroJogadas * 0.1, 1)}")
        print(f"Total time: {(searchTime) + numeroJogadas * 0.1}")
        #print(f"T: {terminals} Non-T: {non_terminals}")
        print(solution)
        print("\n")
        """
        
        """
        start = time.perf_counter()
        solution = test_search("a*",levels()[int(lvl)-1])
        end = time.perf_counter()
        numeroJogadas = len(solution)
        searchTime = round(end - start,1)
        terminals,non_terminals = get_terminals("a*",levels()[int(lvl)-1])
        print(f"A*:")
        print(f"Time of Search: {searchTime} moves: {numeroJogadas}")
        print(f"Time of execut: {round(numeroJogadas * 0.1, 1)}")
        print(f"Total time: {(searchTime) + numeroJogadas * 0.1}")
        print(f"T: {terminals} Non-T: {non_terminals}")
        print(solution)
        print("\n")
        """

##################################################################################
def levels() -> list[str]:
    '''List of levels available in file_name by text'''
    f: TextIOWrapper = open("levels.txt", "r")
    l: list[str] = f.read().split("\n")
    return l

def get_terminals(strategy: str, grid: str) -> tuple[str, str] | None:
    mapa = Map(grid)
    dominio = Dominio(mapa)
    my_prob = SearchProblem(dominio)
    my_tree = SearchTree(my_prob)
    my_tree.strategy = strategy
    my_tree.search()
    return (my_tree.terminals,my_tree.non_terminals)

def get_cost(strategy: str, grid: str) -> tuple[str,str]:
    mapa = Map(grid)
    dominio = Dominio(mapa)
    my_prob = SearchProblem(dominio)
    my_tree = SearchTree(my_prob)
    my_tree.strategy = strategy
    solution_node = my_tree.search()
    return solution_node.cost


def test_search(strategy: str, grid: str) -> List[Tuple[str, str]]:
    mapa = Map(grid)
    dominio = Dominio(mapa)
    my_prob = SearchProblem(dominio)
    my_tree = SearchTree(my_prob)
    my_tree.strategy = strategy
    solution_node = my_tree.search()
    return my_tree.display_plays(solution_node)

if __name__ == "__main__":
    main()
