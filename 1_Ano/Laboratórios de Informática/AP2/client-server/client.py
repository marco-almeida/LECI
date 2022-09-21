#!/usr/bin/python3

import os
import sys
import socket
import json
import base64
from common_comm import send_dict, recv_dict, sendrecv_dict
from Crypto.Cipher import AES

# Função para encriptar valores a enviar em formato jsos com codificação base64
# return int data encrypted in a 16 bytes binary string coded in base64
def encrypt_intvalue(cipherkey, data):
    return None


# Função para desencriptar valores recebidos em formato json com codificação base64
# return int data decrypted from a 16 bytes binary strings coded in base64
def decrypt_intvalue(cipherkey, data):
    return None


# verify if response from server is valid or is an error message and act accordingly
def validate_response(client_sock, response):
    if response["status"] == False:
        print("Erro! Operação incorreta. " + response["error"])
        client_sock.close()
        sys.exit(3)


# process QUIT operation
def quit_action(client_sock, attempts):
    print(f"\nEscolheste desistir! Fizeste {attempts} tentativas.\n")
    client_sock.close()
    sys.exit(4)


# Outcomming message structure:
# { op = "START", client_id, [cipher] }
# { op = "QUIT" }
# { op = "GUESS", number }
# { op = "STOP", number, attempts }
#
# Incomming message structure:
# { op = "START", status, max_attempts }
# { op = "QUIT" , status }
# { op = "GUESS", status, result }
# { op = "STOP", status, guess }


#
# Suporte da execução do cliente
#
def run_client(client_sock, client_id):
    # Operação start
    startOp = sendrecv_dict(client_sock, {"op": "START", "client_id": client_id})
    validate_response(client_sock, startOp)
    # Saudações
    print("\nBem vindo " + client_id + ", ao Guess Game!\n\nTens " +
          str(startOp["max_attempts"]) + " tentativas para acertar no número aleatório de 0 a 100")
    tentativas = 0
    # Interface
    while 1:
        inputClient = input("Tenta adivinhar o número ou escreve quit/QUIT para desistir: ")
        if inputClient == "quit" or inputClient == "QUIT":
            break
        if inputClient.isnumeric():
            if (int(inputClient) < 0 or int(inputClient) > 100):
                print("\nDeve tentar adivinhar números entre 0 e 100 apenas!\n")
                continue
            # Operação guess
            guessOp = sendrecv_dict(client_sock, {"op": "GUESS", "number": int(inputClient)})
            validate_response(client_sock, guessOp)
            tentativas += 1
            tentativasRestantes = startOp["max_attempts"] - tentativas
            resultado = ""
            
            if (guessOp["result"]) == "smaller":
                resultado = "\nDeves jogar um valor menor.\nTentativas efetuadas: " + \
                    str(tentativas) + "\nTentativas restantes: " + \
                    str(tentativasRestantes)+"\n"
            elif (guessOp["result"]) == "larger":
                resultado = "\nDeves jogar um valor maior.\nTentativas efetuadas: " + \
                    str(tentativas) + "\nTentativas restantes: " + \
                    str(tentativasRestantes) + "\n"
            elif (guessOp["result"]) == "equals":
                resultado = "\nParabéns, acertaste!\nTentativas efetuadas: " + \
                    str(tentativas) + "\nTentativas restantes: " + \
                    str(tentativasRestantes)+"\n"
                stopOp = sendrecv_dict(client_sock, {"op": "STOP", "number": int(inputClient), "attempts": int(tentativas)})
                validate_response(client_sock, stopOp)
                print(resultado)
                client_sock.close()
                sys.exit(4)
            print(resultado)
        else:
            print(f"\nOperação {inputClient} inexistente!\n")

    # Operação quit
    quitOp = sendrecv_dict(client_sock, {"op": "QUIT"})
    validate_response(client_sock, quitOp)
    quit_action(client_sock, tentativas)


def main():
    # validate the number of arguments and eventually print error message and exit with error
    # verify type of of arguments and eventually print error message and exit with error

    hostname = "127.0.0.1"
    # Validação de argumentos
    if len(sys.argv) < 3 or len(sys.argv) > 4:
        print("Erro: Número inválido de argumentos!")
        sys.exit(1)
    elif len(sys.argv) == 4:
        hostname = sys.argv[3]
    if not(sys.argv[2].isnumeric()):
        print("Erro: Porto inválido! O porto deve ser um inteiro.")
        sys.exit(2)
    elif int(sys.argv[2]) < 0 or int(sys.argv[2]) > 65535:
        print("Erro: Porto inválido! O porto deve estar entre o intervalo 0-65535.")
        sys.exit(2)

    port = int(sys.argv[2])
    client_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Robustez
    try:
        client_sock.connect((hostname, port))
    except(ConnectionRefusedError):
        print("Erro: Porto inválido! O computador de destino recusou a ligação.")
        client_sock.close()
        sys.exit(2)
    except(socket.gaierror, OSError):
        print("Erro: Hostname não pôde ser resolvido!")
        client_sock.close()
        sys.exit(5)

    run_client(client_sock, sys.argv[1])

    client_sock.close()
    sys.exit(0)


if __name__ == "__main__":
    main()
