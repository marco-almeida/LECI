

import time

from tpi1 import *

               
road_connections = [  # Ligacoes por estrada
                      ('Coimbra', 'Leiria', 73),
                      ('Aveiro', 'Agueda', 35),
                      ('Porto', 'Agueda', 79),
                      ('Agueda', 'Coimbra', 45),
                      ('Viseu', 'Agueda', 78),
                      ('Aveiro', 'Porto', 78),
                      ('Aveiro', 'Coimbra', 65),
                      ('Figueira', 'Aveiro', 77),
                      ('Braga', 'Porto', 57),
                      ('Viseu', 'Guarda', 75),
                      ('Viseu', 'Coimbra', 91),
                      ('Figueira', 'Coimbra', 52),
                      ('Leiria', 'Castelo Branco', 169),
                      ('Figueira', 'Leiria', 62),
                      ('Leiria', 'Santarem', 78),
                      ('Santarem', 'Lisboa', 82),
                      ('Santarem', 'Castelo Branco', 160),
                      ('Castelo Branco', 'Viseu', 174),
                      ('Santarem', 'Evora', 122),
                      ('Lisboa', 'Evora', 132),
                      ('Evora', 'Beja', 105),
                      ('Lisboa', 'Beja', 178),
                      ('Faro', 'Beja', 147),
                      # extra
                      ('Braga', 'Guimaraes', 25),
                      ('Porto', 'Guimaraes', 44),
                      ('Guarda', 'Covilha', 46),
                      ('Viseu', 'Covilha', 57),
                      ('Castelo Branco', 'Covilha', 62),
                      ('Guarda', 'Castelo Branco', 96),
                      ('Lamego','Guimaraes', 88),
                      ('Lamego','Viseu', 47),
                      ('Lamego','Guarda', 64),
                      ('Portalegre','Castelo Branco', 64),
                      ('Portalegre','Santarem', 157),
                      ('Portalegre','Evora', 194) ]

city_coordinates =  {  # City coordinates
                       'Aveiro': (41,215),
                       'Figueira': ( 24, 161),
                       'Coimbra': ( 60, 167),
                       'Agueda': ( 58, 208),
                       'Viseu': ( 104, 217),
                       'Braga': ( 61, 317),
                       'Porto': ( 45, 272),
                       'Lisboa': ( 0, 0),
                       'Santarem': ( 38, 59),
                       'Leiria': ( 28, 115),
                       'Castelo Branco': ( 140, 124),
                       'Guarda': ( 159, 204),
                       'Evora': (120, -10),
                       'Beja': (125, -110),
                       'Faro': (120, -250),
                       #extra
                       'Guimaraes': ( 71, 300),
                       'Covilha': ( 130, 175),
                       'Lamego' : (125,250),
                       'Portalegre': (130,170) }

cidades_portugal = MyCities(road_connections,city_coordinates)
p = SearchProblem(cidades_portugal,'Braga','Faro')

cidades_portugal_as_tuple = ( lambda s : func_actions(road_connections,s),
                              lambda s,a : func_result(s,a),
                              lambda s,a : func_cost(road_connections,s,a),
                              lambda s,goal : func_heuristic(city_coordinates,s,goal),
                              lambda s,gs : func_satisfies(s,gs),
                              func_branching(road_connections,city_coordinates) )

p_as_tuple = (cidades_portugal_as_tuple,'Braga','Faro')



print("\n-- ## Ex. 7 --------------------------------")

t0 = time.process_time()
t = MyTree(p,'IBA*')
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
node=t.all_nodes[t.open_nodes[0]]
print(node.state,node.parent,node.cost,node.heuristic,node.depth)
print("Time:",time.process_time()-t0)
print()

t0 = time.process_time()
t = MyTree(p,'IBA*',keep=0.15)
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
node=t.all_nodes[t.open_nodes[0]]
print(node.state,node.parent,node.cost,node.heuristic,node.depth)
print("Time:",time.process_time()-t0)
print()

t0 = time.process_time()
t = MyTree(p,'IBA*',keep=0.05)
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
node=t.all_nodes[t.open_nodes[0]]
print(node.state,node.parent,node.cost,node.heuristic,node.depth)
print("Time:",time.process_time()-t0)
print("---------------------------------------------")

t0 = time.process_time()
t = MyTree(p,'IBA*',optimize=1)
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
print(t.open_nodes[0])
print("Time:",time.process_time()-t0)
print()

t0 = time.process_time()
t = MyTree(p,'IBA*',optimize=1,keep=0.15)
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
print(t.open_nodes[0])
print("Time:",time.process_time()-t0)
print()

t0 = time.process_time()
t = MyTree(p,'IBA*',optimize=1,keep=0.05)
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
print(t.open_nodes[0])
print("Time:",time.process_time()-t0)
print("---------------------------------------------")


t0 = time.process_time()
t = MyTree(p_as_tuple,'IBA*',optimize=2)
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
print(t.open_nodes[0])
print("Time:",time.process_time()-t0)
print()

t0 = time.process_time()
t = MyTree(p_as_tuple,'IBA*',optimize=2,keep=0.15)
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
print(t.open_nodes[0])
print("Time:",time.process_time()-t0)
print()

t0 = time.process_time()
t = MyTree(p_as_tuple,'IBA*',optimize=2,keep=0.05)
print('IBA*',t.optimize,t.search2(),t.keep)
print(t.non_terminals,t.terminals)
print(t.solution)
print(t.open_nodes[0])
print("Time:",time.process_time()-t0)
print("---------------------------------------------")
