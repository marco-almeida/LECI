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

import socket
import argparse
from cc import getCerts, cert_to_string, cert_from_string, getName
import random
from symmetric import *
from utils import *
from asymmetric import *
from ast import literal_eval

global clients
clients = dict()


def register_caller(s, name):
    pub_key, private_key = generate_rsa_key_pair()
    public_key_str = pem_to_str(public_key_to_pem(pub_key))

    # firstly send my public key
    print(colored(f"Sending my public key. Snippet: {public_key_str[100:110]}", 'blue'))
    message = {'header': 'register', 'public_key': public_key_str}
    send_message(s, message)
    # receive server's public key
    message = recv_message(s)
    print(colored(f"Received playing area's public key. Snippet: {message['public_key'][100:110]}", 'yellow'))
    clients[-1] = Client("Playing Area", s, message['public_key'])

    ### CC ###
    cert_cc = getCerts()
    cert_str = cert_to_string(cert_cc)
    message = {'header': 'register', 'cert': cert_str, 'id': -2}
    send_message(s, message, private_key=private_key, target_public_key=clients[-1].public_key)

    nonceMessage = recv_message(s, clients, private_key)
    nonce = nonceMessage['body']
    message = {'header': 'register', 'id': -2, 'nonce': nonce}
    send_message(s, message, private_key=private_key, authentication=True, target_public_key=clients[-1].public_key)

    # send data
    message = {'header': 'register', 'body': name, 'type': 'caller', 'id': -2} 
    send_message(s, message, private_key=private_key, target_public_key=clients[-1].public_key)

    message = recv_message(s, clients, private_key)
    # handle response
    if message['header'] == 'register_error':
        print(colored(f"Error: {message['body']}", 'red'))
        exit(1)
    else:
        clients[0] = Caller(name, "dummy socket", public_key_str)
        clients[0].private_key = private_key

        print(colored(f"\nRegistered as {name} with id 0 (Caller)", 'blue', attrs=['bold']))


def process_user_info(data):
    # populate dict with new info
    print(colored("\nProcessing user info\n", 'blue'))
    for id in data['body']:
        if int(id) not in clients:
            clients[int(id)] = Player(data['body'][id]['nickname'],
                                      "dummy socket", int(id), data['body'][id]['public_key'].encode())
        print(colored(f"Received info about {data['body'][id]['nickname']} with id {id}", 'yellow'))
        print(colored(f"Snippet of public key: {data['body'][id]['public_key'][100:110]}", 'yellow'))
        if int(id) == 0:
            print("")


def handle_card_info(s, data):
    if not validate_card(N, data['body']):
        print(
            colored(f"Received invalid card: {data['body']} received from {data['card_id']}. Banning player...", 'red'))
        message = {'header': 'ban', 'body': 'Invalid card',
                   'id': 0, 'ban_id': data['card_id']}
        send_message(s, message,private_key=clients[0].private_key,target_public_key=clients[-1].public_key)
        clients.pop(data['card_id'], None)  # remove player from clients dict
        return
    print(colored(f"Received valid card {data['body']} from {data['card_id']}", 'green'))
    clients[data['card_id']].card = data['body']


def start_game(s):
    '''Returns whether the game was started successfully or not'''
    message = {'header': 'start', 'N': N, 'id': 0}
    send_message(s, message, private_key=clients[0].private_key, target_public_key=clients[-1].public_key)

    # server will send info stating if the game was started successfully or there are no players
    message = recv_message(s, clients, clients[0].private_key)
    if message['header'] == 'start_error':
        print(colored(f"Error: {message['body']}", 'red'))
        return False

    return True


def handle_generate_deck(s):
    deck = generate_deck(N)
    my_key = generate_symmetric_key()
    print(colored(f"\nSnippet of my symmetric key: {str(my_key)[10:20]}", 'blue'))
    clients[0].symmetric_key = my_key

    # convert to str and then to bytes
    deck = [str(x).encode() for x in deck]

    # encrypt each number in deck
    encrypted_deck1 = [encrypt_sym_deck(x, my_key) for x in deck]

    # send deck as repr of list of bytes
    message = {'header': 'shuffle_deck',
               'body': repr(encrypted_deck1), 'id': 0}
    print(colored(f"Sending initial deck.\n", 'blue'))
    send_message(s, message, private_key=clients[0].private_key, target_public_key=clients[-1].public_key)


def get_final_deck(s, data):
    deck = literal_eval(data['body'])
    clients[0].deck = deck
    if not validate_deck_enc(N, deck):
        print(colored("\nInvalid deck received. Aborting game...\n", 'red'))
        abort_game(s, "Invalid deck received")
    else:
        print(colored(f"\nValid final deck received\n", 'green'))
        send_message(s, {'header': 'ok', 'id': 0},
                     private_key=clients[0].private_key, target_public_key=clients[-1].public_key)

    # send my symmetric key to server
    message = {'header': 'symmetric_key', 'body': repr(
        clients[0].symmetric_key), 'id': 0}
    send_message(s, message, private_key=clients[0].private_key, target_public_key=clients[-1].public_key)


def process_symmetric_key(s, data):
    clients[data['key_id']].symmetric_key = literal_eval(
        data['body'])

    print(colored(f"Received symmetric key from {data['key_id']}. Snippet: {data['body'][10:20]}", 'yellow'))

    # last symmetric key received
    if all(clients[x].symmetric_key != None for x in clients if x != -1):
        final_deck = clients[0].deck

        for id in sorted(clients.keys(), reverse=True):
            if id != -1:
                final_deck = [decrypt_sym_deck(x, clients[id].symmetric_key)
                              if isinstance(x, bytes) else x for x in final_deck]

        # decode array element by element
        final_deck = [int(x.decode()) for x in final_deck]
        clients[0].deck = final_deck
        print("\nAll symmetric keys have been published. Decrypting final deck...\n")

        if not validate_deck_no_enc(N, final_deck):
            print("Invalid deck received. Aborting game...")
            abort_game(s, "Invalid deck received")

        print_deck(final_deck)

        # calculate bingos
        bingos = get_bingo_winners(final_deck, clients)
        print("Calculated bingos:", colored(bingos, 'blue'))
        clients[0].results = bingos


def process_player_results(s, data):
    clients[data['player_id']].results = data['body']

    if data['body'] != clients[0].results:
        print(colored(f"Received unexpected results {data['body']} from player {data['player_id']}", 'red'))
        clients.pop(data['player_id'], None)
        message = {'header': 'ban', 'body': 'Invalid results',
                   'id': 0, 'ban_id': data['player_id']}
        send_message(s, message, private_key=clients[0].private_key, target_public_key=clients[-1].public_key)
    else:
        print(colored(f"Received expected results {data['body']} from player {data['player_id']}", 'green'))

    if len(clients) == 1:
        print("All players have been banned. Closing game...")
        exit(1)

    # check if all users have sent their results
    if all(clients[x].results != [] for x in clients if x != -1):
        # if results already sent to one player dont send it again
        actual_winners = get_bingo_winners(clients[0].deck, clients)
        print(colored(f"Calculated bingos after bans {actual_winners}", 'blue'))
        send_results(s, actual_winners)
        print(f"Game over. Results sent to all players.")
        option = ''
        while option not in ['y', 'n']:
            option = input("Would you like to audit the log? (y/n) ")
            if option.lower() == 'y':
                message = {'header': 'audit_log', 'id': 0, 'op': 'y'}
                send_message(s, message, private_key=clients[0].private_key, target_public_key=clients[-1].public_key)
                # receive message
                data = recv_message(s, clients, clients[0].private_key)
                print(f"Audit log: {data['body']}")
            elif option.lower() == 'n':
                message = {'header': 'audit_log', 'id': 0, 'op': 'n'}
                send_message(s, message, private_key=clients[0].private_key, target_public_key=clients[-1].public_key)
            else:
                print("Invalid answer. Try again.")
        print("Quitting...")
        exit(1)


def send_results(s, bingos):
    for id in clients:
        if id != 0:
            if id in bingos:  # send congratulations
                message = {'header': 'bingo_winning_results',
                           'body': 'Congratulations, you won!', 'id': 0, 'dest_id': id, 'bingos': bingos}
            else:  # send sorry
                message = {'header': 'bingo_winning_results',
                           'body': 'Sorry, you lost.', 'id': 0, 'dest_id': id, 'bingos': bingos}
            send_message(s, message, private_key=clients[0].private_key, target_public_key=clients[-1].public_key)


def handle_ban_info(data):
    if data['header'] == 'Left':
        print(f"Player {data['ban_id']} left.")
        clients.pop(data['ban_id'], None)


def abort_game(s, cause):
    send_message(s, {'header': 'abort_game', 'id': 0, 'body': cause}, private_key=clients[0].private_key, target_public_key=clients[-1].public_key)
    exit(1)


def act_upon_signature_validity_as_caller(s, data):
    if 'signature_validity' in data:
        if data['signature_validity'] == False:
            print("Hacking detected. Invalid signature received. Aborting game...")
            abort_game(s, "Invalid signature received")


def menu(s, argsNome):
    # register caller
    register_caller(s, argsNome)
    # try to send deck
    starting = False
    while not starting:
        op = input("Start game? (y/n): ")
        if op.lower() == 'y':
            starting = start_game(s)

    while True:
        data = recv_message(s, clients, clients[0].private_key)
        if data == None:
            break

        act_upon_signature_validity_as_caller(s, data)
        if data['header'] == 'user_info':
            process_user_info(data)
        elif data['header'] == 'card':
            handle_card_info(s, data)
        elif data['header'] == 'generate_deck':
            handle_generate_deck(s)
        elif data['header'] == 'final_deck':
            get_final_deck(s, data)
        elif data['header'] == 'symmetric_key':
            process_symmetric_key(s, data)
        elif data['header'] == 'bingo_results':
            process_player_results(s, data)
        elif data['header'] == 'ban_info':
            handle_ban_info(data)


def main():
    parser = argparse.ArgumentParser(description='Bingo Caller')
    parser.add_argument('--port', metavar='N', type=int,
                        nargs='?', help='Port number', default=5000)
    parser.add_argument('--ip', metavar='N', type=str,
                        nargs='?', help='IP address', default='127.0.0.1')
    parser.add_argument('--name', metavar='N', type=str, nargs='?',
                        help='Caller name', default=random.choice(NAMES))  # default=getName())
    parser.add_argument('--decksize', metavar='N', type=int,
                        nargs='?', help='Number of cards in deck', default=16)
    args = parser.parse_args()

    if args.decksize % 4 != 0:
        print("Deck size must be multiple of 4")
        exit(1)

    global N
    N = args.decksize
    PORT = args.port
    IP = args.ip

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((IP, PORT))

        menu(s, args.name)


if __name__ == '__main__':
    main()