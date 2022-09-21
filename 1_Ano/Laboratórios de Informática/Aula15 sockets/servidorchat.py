import socket
import random


def main():
    tentativas = 0
    secretNumber = random.randint(1, 101)
    tcp_s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    tcp_s.bind(("127.0.0.1", 1234))
    # máximo de 1 cliente à espera de
    # aceitação
    tcp_s.listen(1)
    # esperar por novos clientes
    client_s1, client_addr1 = tcp_s.accept()
    print(
        f"Novo cliente com o address {client_addr1} conectou-se ao servidor!")
    print(f"O número secreto é {secretNumber}")
    client_s1.send("Welcome to the Guess Game".encode("utf-8"))

    while 1:
        b_data1 = client_s1.recv(1024)
        decode_data1 = b_data1.decode("utf-8")
        tentativas += 1
        sendString = ""
        if (int(decode_data1) > secretNumber):
            sendString += "Number is too high!"
        elif(int(decode_data1) < secretNumber):
            sendString += "Number is too low!"
        else:
            sendString += "You got it right after " + \
                str(tentativas) + " tries!"
            print(f"O cliente acertou com {tentativas} tentativas!")
            client_s1.shutdown(socket.SHUT_RDWR)
            client_s1.close()
            exit(1)
        print(
            f"O utilizador deu o numero {decode_data1} e até agora fez {tentativas} tentativas. O número secreto é {secretNumber}")
        decodedSendString = sendString.encode("utf-8")
        client_s1.send(decodedSendString)
    client_s1.close()
    tcp_s.close()


main()