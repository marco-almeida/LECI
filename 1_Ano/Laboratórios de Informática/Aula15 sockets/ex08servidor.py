import socket
import select


def main():
    tcp_s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    tcp_s.bind(("127.0.0.1", 1234))
    tcp_s.listen(2)
    # Lista de sockets conhecidos
    socks = []
    client_1, adress_1 = tcp_s.accept()
    print(f"Novo cliente com o address {adress_1} conectou-se ao servideiro!")
    socks.append(client_1)
    client_2, adress_2 = tcp_s.accept()
    print(f"Novo cliente com o address {adress_2} conectou-se ao servideiro!")
    socks.append(client_2)

    while 1:
        rsocks = select.select([client_1, client_2, ], [], [])[0]
        for sock in rsocks:
            if sock == client_1:
                b_data = sock.recv(4096)
                str_data = format("%s:%s" %
                                  ("client_1", b_data.decode("utf-8")))
                print(str_data)
                for c in socks:
                    c.send(str_data.encode("utf-8"))
            if sock == client_2:
                b_data = sock.recv(4096)
                str_data = format("%s:%s" %
                                  ("client_2", b_data.decode("utf-8")))
                print(str_data)
                for c in socks:
                    c.send(str_data.encode("utf-8"))

    tcp_s.close()


main()
