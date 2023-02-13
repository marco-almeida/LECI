# Authors: Marco Almeida (103440), Rui Machado (65081)
from __future__ import annotations
from common_plus import Map
from domain import *
import time
import sys

def main():

    if len(sys.argv) != 2:
        print("ERROR!\nUsage: python3 test_show_level.py <level> ")
        exit(1)
    
    lvl = sys.argv[1]

    if int(lvl) > 57:
        print("Levels go from 1 to 57")
        exit(1)
    ###############################################################################3

    f = open("levels.txt", "r")
    levels_list = f.read().split("\n")
    txt = levels_list[int(lvl)-1]
    pieces, level, movements = txt.split(" ")

    i = 0
    while i < len(level):
        print(level[i] + "  ", end="")
        i += 1
        if i%6 == 0:
            print("\n")

if __name__ == "__main__":
    main()
