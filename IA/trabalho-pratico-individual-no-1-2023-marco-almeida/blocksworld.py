#
#  Module: blocksworld
# 
#  Based on the imported "strips" module, the "blocksworld" module
#  defines a set of predicates and operators for representing
#  the "Blocks World" planning domain.
#
#  (c) Luis Seabra Lopes
#  Inteligência Artificial & Introducao a Inteligencia Artificial, 2019-2020
#


import time
from strips import *

X='X'
Y='Y'
Z='Z'

# Blocks world predicates

class Floor(Predicate):
    def __init__(self,block):
        self.args = [block]

class On(Predicate):
    def __init__(self,b1,b2):
        self.args = [b1,b2]

class Free(Predicate):
    def __init__(self,block):
        self.args = [block]

class Holds(Predicate):
    def __init__(self,block):
        self.args = [block]

class HandFree(Predicate):
    def __init__(self):
        self.args = []


# Blocks world operators

class Stack(Operator):
    args = [X,Y]
    pc   = [Holds(X),Free(Y)]
    neg  = [Holds(X),Free(Y)]
    pos  = [On(X,Y),HandFree(),Free(X)]

class Unstack(Operator):
    args = [X,Y]
    pc   = [On(X,Y),HandFree(),Free(X)]
    neg  = [On(X,Y),HandFree(),Free(X)]
    pos  = [Holds(X),Free(Y)]

class Putdown(Operator):
    args = [X]
    pc   = [Holds(X)]
    neg  = [Holds(X)]
    pos  = [Floor(X),HandFree(),Free(X)]
    
class Pickup(Operator):
    args = [X]
    pc   = [Floor(X),HandFree(),Free(X)]
    neg  = [Floor(X),HandFree(),Free(X)]
    pos  = [Holds(X)]
    

