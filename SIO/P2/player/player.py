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

import argparse
import socket
import random
from symmetric import *
from utils import *
from ast import literal_eval
from cc import getName, getCerts, cert_to_string
from asymmetric import *


clients = dict()


def register_player(s, name):
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

    nonceMessage = recv_message(s, clients,priv_key= private_key)
    nonce = nonceMessage['body']
    message = {'header': 'register', 'id': -2, 'nonce': nonce}
    send_message(s, message, private_key=private_key, authentication=True, target_public_key=clients[-1].public_key)

    message = {'header': 'register', 'body': name, 'type': 'player', 'id': -2} 
    send_message(s, message, private_key=private_key, target_public_key=clients[-1].public_key)
    # get response
    message = recv_message(s, clients, private_key)
    # handle response
    if message['header'] == 'register_error':
        print(colored(f"Error: {message['body']}", 'red'))
        exit(1)
    else:
        global my_id
        my_id = message['dest_id']

        clients[my_id] = Player(name, s, message['dest_id'], public_key_str)
        clients[my_id].private_key = private_key
        print(colored(f"\nRegistered as {name} with given id of {message['dest_id']}\n", 'blue', attrs=['bold']))


def handle_user_info(s, data):
    # populate dict with new info
    global decksize
    decksize = data['N']

    card = generate_card(decksize)
    clients[my_id].card = card
    print(colored(f"My card {card}\n", 'blue'))

    print(colored("Processing user info\n", 'blue'))
    for id in data['body']:
        if int(id) != 0:
            if int(id) not in clients:
                clients[int(id)] = Player(data['body'][id]['nickname'],
                                          "dummy socket", int(id), data['body'][id]['public_key'].encode())
        else:
            clients[int(id)] = Caller(data['body'][id]['nickname'],
                                      "dummy socket", data['body'][id]['public_key'].encode())
        print(colored(f"Received info about {data['body'][id]['nickname']} with id {id}", 'yellow'))
        print(colored(f"Snippet of public key: {data['body'][id]['public_key'][100:110]}", 'yellow'))
        if int(id) == 0:
            print("")

    message = {'header': 'card', 'body': card, 'id': my_id}
    send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)


def handle_card_info(data):
    if not validate_card(decksize, data['body']):
        print(colored(f"Received invalid card: {data['body']} received from {data['card_id']}", 'red'))
    else:
        print(colored(f"Received valid card {data['body']} from {data['card_id']}", 'green'))

    clients[data['card_id']].card = data['body']


def handle_shuffle_deck(s, data):
    deck = literal_eval(data['body'])

    if not validate_deck_enc(decksize, deck):
        print(colored(f"Invalid deck received. Leaving...", 'red'))
        message = {'header': 'error', 'id': my_id}
        send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)
        exit(1)
    else:
        print(colored(f"\nValid deck received. Encrypting and shuffling to send...", 'green'))

    my_key = generate_symmetric_key()
    print(colored(f"\nSnippet of my symmetric key: {str(my_key)[10:20]}", 'blue'))
    clients[my_id].symmetric_key = my_key
    # encrypt each number
    encrypted_deck1 = [encrypt_sym_deck(x, my_key) for x in deck]

    encrypted_deck1 = shuffle_deck(encrypted_deck1)

    message = {'header': 'shuffle_deck',
               'body': repr(encrypted_deck1), 'id': my_id}
    send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)


def handle_final_deck_message(s, data):
    deck = literal_eval(data['body'])
    clients[0].deck = deck

    if not validate_deck_enc(decksize, deck):
        print(colored("Invalid final encrypted deck received. Leaving...\n", 'red'))
        message = {'header': 'error', 'id': my_id}
        #send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)
        exit(1)
    else:
        print(colored("Valid final encrypted deck received\n", 'green'))

    # send my symmetric key to server
    message = {'header': 'symmetric_key', 'body': repr(
        clients[my_id].symmetric_key), 'id': my_id}
    send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)


def handle_symmetric_key(s, data):
    clients[data['key_id']].symmetric_key = literal_eval(
        data['body'])  # Está certo

    print(colored(f"Received symmetric key from {data['key_id']}. Snippet: {data['body'][10:20]}", 'yellow'))

    # last symmetric key received

    if all(clients[x].symmetric_key != None for x in clients if x != -1):
        final_deck = clients[0].deck

        for id in sorted(clients.keys(), reverse=True):
            if id != -1:
                final_deck = [decrypt_sym_deck(x, clients[id].symmetric_key) if isinstance(
                    x, bytes) else x for x in final_deck]

        # decode array element by element
        final_deck = [int(x.decode()) for x in final_deck]
        clients[0].deck = final_deck
        print("\nAll symmetric keys have been published. Decrypting final deck...\n")
        print_deck(final_deck)

        if not validate_deck_no_enc(decksize, final_deck):
            print("Invalid deck received. Leaving...")
            message = {'header': 'error', 'id': my_id}
            send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)
            exit(1)

        # calculate bingos
        bingos = get_bingo_winners(final_deck, clients)

        traquinice = random.randint(1, 30)
        if traquinice == 1:
            if my_id not in bingos:
                print(colored("I'm cheating by adding my id to the bingo results.\n", 'red', attrs=['bold']))
                bingos += [my_id]
            else:
                print(colored("I'm misbehaving by altering the bingo results\n", 'red', attrs=['bold']))
                bingos += [my_id]

        print(colored(f"Sending my bingo results to caller: {bingos}", 'blue'))
        clients[my_id].results = bingos
        # send bingo results to server
        message = {'header': 'bingo_results', 'body': bingos, 'id': my_id, 'player_id': my_id}
        send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)


def handle_player_results(data):
    print(f"Received results {data['body']} from player {data['id']}")
    clients[data['id']].results = data['body']
    if clients[my_id].results != clients[data['id']].results:
        print(f"Unexpected results: {clients[data['id']].results}")


def handle_ban(data):
    print(colored(f"Banned for cheating. Cause: {data['body']}", 'red'))
    exit(1)


def handle_ban_info(data):
    if data['body'] == 'Left':
        print(colored(f"Player {data['ban_id']} left the game.", 'yellow'))
    else:
        print(colored(f"Player {data['ban_id']} was banned for cheating. Cause: {data['body']}", 'yellow'))
    clients.pop(data['ban_id'], None)


def handle_abort_game(data):
    print(f"Game aborted. Cause: {data['body']}\n")
    exit(1)


def act_upon_signature_validity_as_player(data):
    if 'signature_validity' in data:
        if data['signature_validity'] == False:
            print(f"Signature invalid. Leaving...")
            exit(1)


def handle_bingo_results(s, data):
    clr = 'green' if data['body'] == 'Congratulations, you won!' else 'red'
    print(colored(f"Received bingo results from caller: {data['bingos']}", 'yellow'))
    print(colored(data['body'], clr, attrs=['bold']))
    option = ''
    while option not in ['y', 'n']:
        option = input("Would you like to audit the log? (y/n) ")
        if option.lower() == 'y':
            message = {'header': 'audit_log', 'id': my_id, 'op': 'y'}
            send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)
            data = recv_message(s, clients, clients[my_id].private_key)
            print(f"Audit log: {data['body']}")
        elif option.lower() == 'n':
            message = {'header': 'audit_log', 'id': my_id, 'op': 'n'}
            send_message(s, message, private_key=clients[my_id].private_key, target_public_key=clients[-1].public_key)
        else:
            print("Invalid answer. Try again.")
    print("Quitting...")
    exit(1)


def main():
    parser = argparse.ArgumentParser(description='Bingo Caller')
    parser.add_argument('--port', metavar='N', type=int,
                        nargs='?', help='Port number', default=5000)
    parser.add_argument('--ip', metavar='N', type=str,
                        nargs='?', help='IP address', default='127.0.0.1')
    parser.add_argument('--name', metavar='N', type=str, nargs='?',
                        help='Caller name', default=random.choice(NAMES))  # default=getName())
    args = parser.parse_args()

    PORT = args.port
    IP = args.ip

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((IP, PORT))

        # register player
        register_player(s, args.name)

        while True:

            data = recv_message(s, clients, clients[my_id].private_key)
            if data == None:
                break
            act_upon_signature_validity_as_player(data)
            if data['header'] == 'user_info':
                handle_user_info(s, data)
            elif data['header'] == 'card':
                handle_card_info(data)
            elif data['header'] == 'shuffle_deck':
                handle_shuffle_deck(s, data)
            elif data['header'] == 'ban':
                handle_ban(data)
            elif data['header'] == 'ban_info':
                handle_ban_info(data)
            elif data['header'] == 'final_deck':
                handle_final_deck_message(s, data)
            elif data['header'] == 'symmetric_key':
                handle_symmetric_key(s, data)
            elif data['header'] == 'bingo_winning_results':
                handle_bingo_results(s, data)
            elif data['header'] == 'abort_game':
                handle_abort_game(data)


if __name__ == "__main__":
    main()