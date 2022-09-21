import socket


def main():
    tcp_s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    tcp_s.bind(("127.0.0.1", 1234))
    # máximo de 1 cliente à espera de
    # aceitação
    tcp_s.listen(1)
    # esperar por novos clientes
    client_s, client_addr = tcp_s.accept()
    print(f"Novo cliente com o address {client_addr} conectou-se ao servideiro!")

    while 1:
        b_data = client_s.recv(1024)
        decode_data = b_data.decode("utf-8")
        print(f"Recebi isto --> {decode_data}")
        client_s.send(b_data.upper())
    client_s.close()
    tcp_s.close()


main()
