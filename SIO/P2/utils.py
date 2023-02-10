import random
import messages as m
from abc import ABC
import json
import itertools
from cc import *
from asymmetric import *
from termcolor import colored


class Client(ABC):  # Abstract Base Class
    def __init__(self, nickname, socket, public_key="DUMMY PUBLIC KEY", id=-1):
        self.id = id
        self.socket = socket
        self.nickname = nickname
        self.public_key = public_key
        self.private_key = None
        self.symmetric_key = None
        self.results = []

    def __str__(self):
        return f'{self.id}: {self.nickname} : {self.public_key[100:120]}'

    def __eq__(self, other):
        return self.id == other.id


class Player(Client):
    id_iter = itertools.count(start=1)  # sequential id

    def __init__(self, nickname, socket, id=None, public_key="DUMMY PUBLIC KEY"):
        super().__init__(nickname, socket, public_key)
        if not id:  # if id is not specified, generate a new one. this happens in playing area
            self.id = next(Player.id_iter)
        else:
            # this happens in user/caller.py where we do not want to increment the global id, and already know it
            self.id = id
        self.card = []

    def __str__(self):
        return "Player " + super().__str__() + f' : {self.card}'


class Caller(Client):
    def __init__(self, nickname, socket, public_key="DUMMY PUBLIC KEY"):
        super().__init__(nickname, socket, public_key)
        self.id = 0
        self.deck = []

    def __str__(self):
        return "Caller " + super().__str__() + f' : {self.deck}'


NAMES = ['John', 'Peter', 'Paul', 'Mary', 'Jane', 'Sarah', 'Joan', 'Emma', 'Olivia', 'Ava', 'Isabella', 'Sophia', 'Charlotte', 'Mia', 'Amelia', 'Harper', 'Evelyn', 'Abigail', 'Emily', 'Elizabeth', 'Mila', 'Ella', 'Avery', 'Sofia', 'Camila', 'Aria', 'Scarlett', 'Victoria', 'Madison', 'Luna', 'Grace', 'Chloe', 'Penelope', 'Layla',
         'Riley', 'Zoey', 'Nora', 'Lily', 'Eleanor', 'Hannah', 'Lillian', 'Addison', 'Aubrey', 'Ellie', 'Stella', 'Natalie', 'Zoe', 'Leah', 'Hazel', 'Violet', 'Aurora', 'Savannah', 'Audrey', 'Brooklyn', 'Bella', 'Claire', 'Skylar', 'Isla', 'Genesis', 'Lucy', 'Paisley', 'Everly', 'Anna', 'Caroline', 'Nova', 'Emilia', 'Kennedy', 'Saman']


def send_message(sock, data, private_key=None, authentication=False, target_public_key=None):
    '''sign message with my private_key and encrypt it with target's public_key'''
    if authentication:
        data2 = json.dumps(data)
        cc_sign = signCC(data2)
        data['cc_sign'] = cert_to_string(cc_sign)

    elif private_key and target_public_key:
        target_public_key = pem_to_public_key(str_to_pem(target_public_key))

        message = json.dumps(data).encode("utf-8")  # bytes

        # assinar bytes nao encriptados
        signature = sign_message(message, private_key)

        # encriptar bytes
        encrypted_message = HybridCipher().encrypt(rsa_public_key=target_public_key, data=json.loads(message.decode()))
        data = {"message": encrypted_message, "signature": signature.hex(), 'id': data['id']}

    data = json.dumps(data)
    data = data.encode('UTF-8')
    m.send_msg(sock, data)


def recv_message(sock, clients=None, priv_key=None, certi=None):
    data = m.recv_msg(sock)

    if data is None:
        return None

    data = json.loads(data.decode("utf-8"))

    sender_public_key = None

    if 'signature' in data:
        if 'id' not in data or data['id'] not in clients:
            sender_public_key = None
        else:
            sender_public_key = pem_to_public_key(str_to_pem(clients[data['id']].public_key))

    if sender_public_key and priv_key:
        decrypted_message = HybridCipher().decrypt(
            rsa_private_key=priv_key, cipher_text=data['message']
        )

        signature = bytes.fromhex(data['signature'])

        if not verify_signature(json.dumps(decrypted_message).encode(), signature, sender_public_key):
            signature_validity = False
        else:
            signature_validity = True
        data = decrypted_message
        data['signature'] = signature.hex()
        data['signature_validity'] = signature_validity

    if certi:
        if 'cc_sign' in data:
            cert_bytes = certi
            datasigned = cert_from_string(data['cc_sign'])
            del data['cc_sign']
            data = json.dumps(data)
            
            if not verifySign(cert_bytes, data, datasigned):
                data = json.loads(data)
                data['header'] = 'invalid_cc'
                data = json.dumps(data)

            data = json.loads(data)

    return data


def generate_deck(decksize):
    N = decksize
    traquinice = random.randint(1, 30)
    if traquinice == 1:
        print(colored("I'm misbehaving by altering the deck\n", 'red', attrs=['bold']))
        return random.sample(range(1, N+1), N) + [1]
    return random.sample(range(1, N+1), N)


def generate_card(decksize):
    N = decksize
    M = (int)(N/4)
    traquinice = random.randint(1, 30)
    if traquinice == 1:
        print(colored("I'm misbehaving by altering the card\n", 'red', attrs=['bold']))
        return random.sample(range(1, N+1), M) + [1]
    return random.sample(range(1, N+1), M)


def validate_deck_enc(N, first_deck):
    '''Returns whether the deck is valid or not'''
    if len(first_deck) != N:
        return False

    if len(first_deck) != len(set(first_deck)):
        return False

    return True


def validate_deck_no_enc(N, first_deck):
    # if all the numbers are in the range [1, N]
    for i in range(len(first_deck)):
        if first_deck[i] < 1 or first_deck[i] > N:
            return False

    return validate_deck_enc(N, first_deck)


def validate_card(decksize, player_card):
    N = decksize
    M = (int)(N/4)
    for number in player_card:
        if number > N or number < 1:
            return False

    if len(set(player_card)) != len(player_card):
        return False

    if len(player_card) != M:
        return False

    return True


def shuffle_deck(deck):
    random.shuffle(deck)
    traquinice = random.randint(1, 30)
    if traquinice == 1:
        print(colored("I'm misbehaving by altering the deck\n", 'red', attrs=['bold']))
        return deck + [1]
    return deck


def print_deck(deck):
    for i in range(len(deck) // 4):
        print(str(deck[i*4:i*4+4]).replace('[', '').replace(']',
              '').replace(',', ',\t'))
    print()


def subset(playerCard, called_numbers):
    return all(x in called_numbers for x in playerCard)


def play_bingo(deck, user_card):
    '''Play bingo with a deck of cards and a user card
    Returns the number of calls to fill the card'''
    # make full copy of deck
    tmp_deck = deck[:]
    called_numbers = []
    fill_calls = 0

    while True:
        try:
            called_numbers.append(tmp_deck.pop(0))
        except:
            break
        fill_calls += 1

        if subset(user_card, called_numbers):
            break

    return fill_calls


def get_bingo_winners(final_deck, players):
    '''Get the winners of the bingo game
    Returns a list of winners'''
    # get copy of players but without the caller/playing area
    players = players.copy()
    players.pop(0, None)
    players.pop(-1, None)

    players_results = [play_bingo(
        final_deck, players[player].card) for player in players]

    winner_ids = [players[list(players.keys())[i]].id for i, x in enumerate(
        players_results) if x == min(players_results)]

    return sorted(winner_ids)
