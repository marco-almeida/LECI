# Authors: Marco Almeida (103440), Rui Machado (65081)
from __future__ import annotations
import asyncio
import json
import getpass
import os
from common_plus import Coordinates, Map

import websockets
from domain import search_path

DIRECTION_DICT = {'a':Coordinates(-1,0), 'd':Coordinates(1,0), 's':Coordinates(0,1), 'w':Coordinates(0,-1)}

def get_car_closest_coordinate_to_cursor(mapa: Map, piece: str, cursor_coords: Coordinates) -> Coordinates:
    piece_coords: list[Coordinates] = mapa.piece_coordinates(piece)
    distances: list[int] = []
    for coord in piece_coords:
        distances.append(abs(coord.x - cursor_coords.x) + abs(coord.y - cursor_coords.y))
    indexes = [index for index, item in enumerate(distances) if item == min(distances)] # Pode haver mais que 1
    return piece_coords[indexes[0]]                                                     

def go_to_piece_or_select(mapa: Map, cursor: Coordinates, piece: str):
    piece_coords = get_car_closest_coordinate_to_cursor(mapa, piece, cursor)
    if cursor.x > piece_coords.x:
        return "a"
    if cursor.x < piece_coords.x:
        return "d"
    if cursor.y > piece_coords.y:
        return "w"
    if cursor.y < piece_coords.y:
        return "s"
    return " "

def get_key(new_coords, old_coords):               # Esta funçao podia estar dentro do translate_move_to_key
    if new_coords[0].x - old_coords[0].x <= -1:    # Mas desta forma é mais legível 
        return 'a'
    elif new_coords[0].x - old_coords[0].x >= 1:
        return 'd'
    elif new_coords[0].y - old_coords[0].y <= -1 :
        return 'w'
    elif new_coords[0].y - old_coords[0].y >= 1:
        return 's'
    
def translate_move_to_key(state: dict, move: tuple[str,str], current_map: Map):
    cursor_coords = Coordinates(state.get("cursor")[0], state.get("cursor")[1])

    if (state.get("selected") == move[0]): # Se a peça/move[0] estiver selecionada
        key = get_key(move[1],current_map.piece_coordinates(move[0]))
        direction = DIRECTION_DICT[key]
        
        if current_map.move_v2(move[0], direction) == 'error':
            return ""
    else:
        if (state.get("selected") != ""): # Largar peça após efectuar o movimento
            key = " "
        else:
            key = go_to_piece_or_select(current_map, cursor_coords, move[0]) # type: ignore
    return key

async def agent_loop(server_address="localhost:8000", agent_name="student"):

    """Example client loop."""
    async with websockets.connect(f"ws://{server_address}/player") as websocket:
        moves: list[tuple[str,str]] = []
        current_level: int = 0

        # Receive information about static game properties
        await websocket.send(json.dumps({"cmd": "join", "name": agent_name}))
        
        #Inicialization of variables: Avoids referenced before assignment in first cycle
        while True:
            try: 
                state = json.loads(await websocket.recv()) # receive game update 

                if state.get("level") != current_level: # Empty moves when level changes
                    current_level = state.get("level")
                    moves = []

                current_map = Map(state.get("grid"))
                if moves: # if it has moves execute solution
                    while current_map.piece_coordinates(moves[0][0]) == moves[0][1]: # Se a proxima jogada já estiver concretizada (Crazy step a nosso favor)
                        moves.pop(0)                                                 # skip para a jogada seguinte
                    
                    key = translate_move_to_key(state, moves[0], current_map) 
                    print(f"KEY: {key}")
                    if key != '': # moves are correct
                        await websocket.send(json.dumps({"cmd": "key", "key": key}))  # send key command to server
                        continue
                
                # if we get here, moves were not correct and so, a new path will be calculated
                search_strat = "a*"
                moves = search_path(search_strat, state.get("grid"),state.get("cursor")) #
                
                goal_y = current_map.piece_coordinates("A")[0].y
                goal_x = state.get("dimensions")[0]-1
                goal = Coordinates(goal_x,goal_y)
                moves.append(('A',[goal]))

                key = translate_move_to_key(state, moves[0], current_map)
                print(f"KEY: {key}")
                await websocket.send(json.dumps({"cmd": "key", "key": key}))  # send key command to server

            except websockets.exceptions.ConnectionClosedOK:
                print("Server has cleanly disconnected us")
                return


##############################################################
loop = asyncio.get_event_loop()
SERVER = os.environ.get("SERVER", "localhost")
PORT = os.environ.get("PORT", "8000")
NAME = os.environ.get("NAME", getpass.getuser())
loop.run_until_complete(agent_loop(f"{SERVER}:{PORT}", NAME))
