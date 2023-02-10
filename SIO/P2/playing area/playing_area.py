#!/bin/python

from pathlib import Path
import os
import sys
try:
    sys.path.index(str(Path(os.getcwd()).parent))
    sys.path.index(str(Path(os.getcwd())))
except ValueError:
    sys.path.append(str(Path(os.getcwd())))
    sys.path.append(str(Path(os.getcwd()).parent))

import hashlib
import socket
import selectors
from utils import *
import argparse
import logging
from symmetric import *
from asymmetric import *
from ast import literal_eval
import datetime
import string

global clients, game_ongoing, logger, my_pub_key, my_priv_key
clients = dict()
game_ongoing = False
(my_pub_key, my_priv_key) = generate_rsa_key_pair()


def unregister_and_close_client(selector, key):
    selector.unregister(key)
    key.close()


def get_random_string(length):
    # choose from all lowercase letter
    letters = string.ascii_lowercase
    result_str = ''.join(random.choice(letters) for i in range(length))
    return result_str

def handle_client_registration(selector, key, data):
    global logger

    client_public_key = data['public_key']
    # send my public key to client
    message = {'header': 'register', 'public_key': pem_to_str(public_key_to_pem(my_pub_key))}
    send_message(key.fileobj, message)
    # temporariously instantiante client
    clients[-2] = Client('dummy', key.fileobj, client_public_key, -2)

    #receive certificate
    data1 = recv_message(key.fileobj,clients= clients,priv_key= my_priv_key)
    cert = cert_from_string(data1['cert'])

    randString = get_random_string(10)
    # send random string to client
    message = {'header': 'nonce', 'body': randString, 'id': -1}
    send_message(key.fileobj, message,private_key= my_priv_key,target_public_key= client_public_key)

    # receive encrypted random string
    data2 = recv_message(key.fileobj, clients=clients, certi=cert)
    is_cc_valid = data2['header']

    # aqui é que recebo o invalid cc
    if game_ongoing:
        print("Client connection error: Game already started\nClient removed")
        # warn client that game already started
        response = {'header': 'register_error', 'body': 'Game already started', 'id': -1}
        send_message(key.fileobj, response,private_key= my_priv_key,target_public_key= client_public_key)

        unregister_and_close_client(selector, key.fileobj)
        clients.pop(-2, None)
        logger.info(f"Client connection error: Game already started", data)
        return

    data = recv_message(key.fileobj, clients, my_priv_key)
    nickname = data['body']
    if is_cc_valid == 'invalid_cc':
        print(
            "Client connection error: Client has invalid CC signature\nClient removed")
        logger.info(
            f"Client connection error: Client {nickname} has invalid CC signature", data)
        response = {'header': 'register_error',
                    'body': 'Invalid CC signature', 'id': -1}
        send_message(key.fileobj, response,private_key= my_priv_key,target_public_key= client_public_key)
        unregister_and_close_client(selector, key.fileobj)
        clients.pop(-2, None)
        return
    
    if data['type'] == 'caller':
        # if theres already a caller entity, reject connection
        if any(isinstance(clients[id], Caller) for id in clients):
            # se o caller ja estiver conectado no passado ou
            logger.info(
                f"Caller connection error: Caller already connected", data)
            print("Caller connection error: Caller already connected\nClient removed")
            response = {'header': 'register_error',
                        'body': 'Caller already connected', 'id': -1}
            send_message(key.fileobj, response,private_key= my_priv_key,target_public_key= client_public_key)
            unregister_and_close_client(selector, key.fileobj)
            clients.pop(-2, None)
            return
        else:
            caller = Caller(nickname, key.fileobj, client_public_key)
            clients[0] = caller
            clients.pop(-2, None)
            logger.info(f"{caller} registered", data)
            response = {'header': 'register_success', 'body': 'Caller connected', 'id': -1}
            send_message(key.fileobj, response,private_key= my_priv_key,target_public_key= client_public_key)
            print(f"{caller} registered \n")

    if data['type'] == 'player':
        player = Player(nickname, key.fileobj, public_key=client_public_key)
        clients[player.id] = player
        clients.pop(-2, None)
        response = {'header': 'register_success', 'body': 'Player connected', 'dest_id':player.id,'id': -1}
        send_message(key.fileobj, response, private_key= my_priv_key, target_public_key= client_public_key)
        logger.info(f"{player} registered", data)


def send_start_error(socket):
    print("Caller attempted to start game. Not enough players")
    response = {'header': 'start_error', 'body': 'Not enough players', 'id': -1}
    send_message(socket, response, private_key=my_priv_key, target_public_key=clients[0].public_key)


def send_start_success(s):
    global game_ongoing
    game_ongoing = True

    response = {'header': 'start_success', 'body': 'Game started', 'id': -1}
    send_message(s, response, private_key=my_priv_key, target_public_key=clients[0].public_key)


def get_clients_info():
    new_dict = {}
    for id in clients:
        tmp_dict = {'public_key': clients[id].public_key,
                    'nickname': clients[id].nickname, 'id': id}
        new_dict[id] = tmp_dict
    return new_dict


def send_info_to_everyone(clients, data):
    # now we must send the user list to everyone, and inform everyone of size of N
    new_dict = get_clients_info()
    # send user list to everyone
    message = {'header': 'user_info', 'body': new_dict, 'N': data['N'], 'id': -1}

    for id in clients:
        send_message(clients[id].socket, message, private_key=my_priv_key, target_public_key=clients[id].public_key)
        # receive signed card from player
        if id != 0:
            data = recv_message(clients[id].socket, clients, my_priv_key)
            logger.info(f"Received card from {clients[id]}", data)
            clients[id].card = data['body']


def send_card_to_everyone(clients):
    # by now we have all the cards from all the players
    # we must now send the cards to everyone

    for card_sender_id in clients:
        if card_sender_id != 0:
            for receiver_id in clients:
                if card_sender_id != receiver_id:
                    message = {'header': 'card',
                               'body': clients[card_sender_id].card, 'id': -1, 'card_id': card_sender_id}
                    send_message(clients[receiver_id].socket, message, private_key=my_priv_key,
                                 target_public_key=clients[receiver_id].public_key)


def handle_game_start(data, s):
    # check if theres at least 1 player in order to be able to start the game
    if len(clients.keys()) < 2:
        send_start_error(s)
        return

    send_start_success(s)
    input(colored("Press enter to distribute user info to everyone", 'green', attrs=['bold']))
    send_info_to_everyone(clients, data)
    input(colored("Press enter to distribute cards to everyone", 'green', attrs=['bold']))
    send_card_to_everyone(clients)

    # ask caller to generate deck
    message = {'header': 'generate_deck', 'id': -1}
    send_message(clients[0].socket, message, private_key=my_priv_key, target_public_key=clients[0].public_key)

###########################################


def handle_deck_shuffling(data):
    banned_players = []
    for id in list(clients):
        if isinstance(clients[id], Player):
            data['id'] = -1
            send_message(clients[id].socket, data, private_key=my_priv_key, target_public_key=clients[id].public_key)
            response = recv_message(clients[id].socket, clients, my_priv_key)
            if response['header'] == 'error':
                banned_players.append(id)
                logger.info(f"Player {clients[id]} disconnected upon receiving deck to be shuffled", data)
                clients.pop(id, None)
            else:
                data = response
                logger.info(f"Received shuffled deck from {clients[id]}", data)

    # if any player was banned, we must send ban_info to everyone
    if len(banned_players) > 0:
        for id in clients:
            for banned_id in banned_players:
                message = {'header': 'ban_info', 'body': 'Left', 'id': -1, 'ban_id': banned_id}
                send_message(clients[id].socket, message, private_key=my_priv_key,
                             target_public_key=clients[id].public_key)

    return data['body']


def send_final_deck(deck):
    message = {'header': 'final_deck', 'body': deck, 'id': -1}
    for id in clients:
        send_message(clients[id].socket, message, private_key=my_priv_key, target_public_key=clients[id].public_key)
        if id == 0:  # caller will validate deck
            response = recv_message(clients[id].socket, clients, my_priv_key)
    if response['header'] == 'abort_game':
        handle_abort_game(response)


def receive_symmetric_keys():
    for id in clients:
        data = recv_message(clients[id].socket, clients, my_priv_key)
        logger.info(f"Received symmetric key from {clients[id]}", data)
        clients[id].symmetric_key = literal_eval(data['body'])


def send_symmetric_keys():
    for key_sender_id in clients:
        for receiver_id in clients:
            if key_sender_id != receiver_id:
                message = {'header': 'symmetric_key', 'body': repr(
                    clients[key_sender_id].symmetric_key), 'id': -1, 'key_id': key_sender_id}
                send_message(clients[receiver_id].socket, message, private_key=my_priv_key,
                             target_public_key=clients[receiver_id].public_key)


def apply_symmetric_keys(deck):
    final_deck = literal_eval(deck)  # Está certo
    for id in sorted(clients.keys(), reverse=True):

        final_deck = [decrypt_sym_deck(x, clients[id].symmetric_key) if isinstance(
            x, bytes) else x for x in final_deck]

    return final_deck


def handle_initial_deck(data):
    ###########################################
    input(colored("Press enter to start the process of shuffling the deck", 'green', attrs=['bold']))
    deck = handle_deck_shuffling(data)
    input(colored("Press enter to distribute the final deck", 'green', attrs=['bold']))
    send_final_deck(deck)
    receive_symmetric_keys()
    input(colored("Press enter to distribute the symmetric keys", 'green', attrs=['bold']))
    send_symmetric_keys()
    input(colored("Press enter to start the process of calculating bingo results", 'green', attrs=['bold']))
    final_deck = apply_symmetric_keys(deck)
    final_deck = [int(x.decode()) for x in final_deck]
    clients[0].deck = final_deck
############################################


def handle_ban(data, selector):
    logger.info(f"Received ban message from caller", data)
    print(
        f"Caller has detected cheating from player with id {data['ban_id']}. Cause: {data['body']} Disconnecting player...\n")
    # send msg to client stating that he has been banned
    response = {'header': 'ban', 'body': data['body'], 'id': -1}
    send_message(clients[data['ban_id']].socket, response, private_key=my_priv_key,
                 target_public_key=clients[data['ban_id']].public_key)
    unregister_and_close_client(selector, clients[data['ban_id']].socket)
    clients.pop(data['ban_id'], None)

    for id in clients:
        if isinstance(clients[id], Player):
            response = {'header': 'ban_info',
                        'body': data['body'], 'ban_id': data['ban_id'], 'id': -1}
            send_message(clients[id].socket, response, private_key=my_priv_key,
                         target_public_key=clients[id].public_key)


def handle_player_results(data):
    logger.info(f"Received bingo results from player {data['id']}", data)
    data['id'] = -1
    send_message(clients[0].socket, data, private_key=my_priv_key, target_public_key=clients[0].public_key)


def handle_send_bingo_results(data):
    logger.info(f"Received caller bingo results", data)
    for id in clients:
        if data['dest_id'] == id:
            data['id'] = -1
            send_message(clients[id].socket, data, private_key=my_priv_key, target_public_key=clients[id].public_key)


def handle_abort_game(data):
    # send info to all players to leave game, as it was aborted by caller
    print(f"Caller aborting game. Cause: {data['body']}\n")
    exit(1)


def get_audit_log():
    with open('log.log', 'r') as f:
        log = f.read()
    return log


def get_user_list():
    return [(id, clients[id].nickname) for id in clients if isinstance(clients[id], Player)]


def handle_audit_log(data):
    if data['op'] == 'y':
        message = {'header': 'audit_log', 'body': get_audit_log(), 'id': -1}
        send_message(clients[data['id']].socket, message, private_key=my_priv_key,
                     target_public_key=clients[data['id']].public_key)
        print(f"Audit log sent to {data['id']}")

    if len(clients) == 1:
        print(f"{data['id']} disconnected")
        # clear log.log
        global logger, game_ongoing
        logger = MyLogger()
        game_ongoing = False
        clients.clear()
        print("Client data and log cleared. Ready for new game!\n")


def handle_client_data(selector, key, data):
    if data['header'] == 'register' or data['header'] == 'invalid_cc':
        handle_client_registration(selector, key, data)
    elif data['header'] == 'start':
        handle_game_start(data, key.fileobj)
    elif data['header'] == 'abort_game':
        handle_abort_game(data)
    elif data['header'] == 'shuffle_deck':
        handle_initial_deck(data)
    elif data['header'] == 'ban':
        handle_ban(data, selector)
    elif data['header'] == 'bingo_results':
        handle_player_results(data)
    elif data['header'] == 'bingo_winning_results':
        handle_send_bingo_results(data)
    elif data['header'] == 'audit_log':
        handle_audit_log(data)


def dispatch(srv_socket):
    selector = selectors.DefaultSelector()
    srv_socket.setblocking(False)
    selector.register(srv_socket, selectors.EVENT_READ, data=None)

    print('Starting Playing Area\n')

    while True:
        events = selector.select(timeout=None)
        for key, mask in events:
            # Check for a new client connection
            if key.fileobj == srv_socket:
                clt_socket, clt_addr = srv_socket.accept()
                clt_socket.setblocking(True)

                # Add it to the sockets under scrutiny
                selector.register(clt_socket, selectors.EVENT_READ, data=None)

            # Client data is available for reading
            else:
                data = recv_message(key.fileobj, clients, my_priv_key)

                if data == None:  # Socket closed
                    # remove client from clients dictionary
                    for id in list(clients):  # has to be list(dict) to avoid RuntimeError
                        if clients[id].socket == key.fileobj:
                            print(f'{id} disconnected')
                            clients.pop(id, None)
                    unregister_and_close_client(selector, key.fileobj)
                    continue

                handle_client_data(selector, key, data)


class MyLogger():

    def __init__(self):
        self.logger = logging.getLogger(__name__)
        syslog = logging.StreamHandler()
        logging.basicConfig(filename="log.log", filemode='w',
                            format='%(sequence_number)03d, %(asctime)s, %(prev_record)s, %(message)s, %(signature)s', datefmt="%Y-%m-%d %H:%M:%S")
        formatter = logging.Formatter(
            '%(sequence_number)03d, %(asctime)s, %(prev_record)s, %(message)s, %(signature)s',
            "%Y-%m-%d %H:%M:%S")
        syslog.setFormatter(formatter)
        self.logger.setLevel(logging.INFO)
        self.logger.addHandler(syslog)
        self.sequence_number = 1
        self.prev_record = None
        self.entries = []

    def info(self, msg, data):
        if self.sequence_number == 1:
            with open('log.log', 'w') as f:
                f.write('')

        if 'signature' in data:
            signature = data['signature']
        else:
            signature = "No signature for this log entry"

        prev = hashlib.sha256(self.prev_record.encode(
            'utf-8')).hexdigest() if self.prev_record is not None else None

        extra = {'sequence_number': self.sequence_number,
                 'prev_record': prev,
                 'signature': signature}

        logger = logging.LoggerAdapter(self.logger, extra)
        logger.info(msg)
        this_message = f"{self.sequence_number:03d}, {datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')}, {prev}, {msg}, {signature}"
        self.prev_record = this_message
        self.sequence_number += 1


logger = MyLogger()


def main():

    parser = argparse.ArgumentParser(description='Playing Area Server')
    parser.add_argument('--port', metavar='N', type=int,
                        nargs='?', help='Port number', default=5000)
    parser.add_argument('--ip', metavar='N', type=str,
                        nargs='?', help='IP address', default='127.0.0.1')
    args = parser.parse_args()

    PORT = args.port
    IP = args.ip

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((IP, PORT))
        s.listen()
        dispatch(s)


if __name__ == '__main__':
    main()