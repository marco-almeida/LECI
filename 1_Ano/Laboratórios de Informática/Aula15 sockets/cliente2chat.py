import socket


def main():
    tcp_s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    tcp_s.bind(("127.0.0.1", 0))
    tcp_s.connect((("127.0.0.1", 1234)))

    while 1:
        frase = input("Fala no chat: ")
        data = frase.encode("utf-8")
        tcp_s.send(data)
        b_data = tcp_s.recv(1024)
        print("addr1:" + b_data.decode("utf-8"))
    tcp_s.close()


main()
