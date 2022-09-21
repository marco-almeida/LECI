import socket


def main():
    tcp_s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    tcp_s.bind(("127.0.0.1", 0))
    tcp_s.connect((("127.0.0.1", 1234)))
    w_data = tcp_s.recv(1024)
    print(w_data.decode("utf-8"))

    while 1:
        frase = input("Try to Guess the number: ")
        data = frase.encode("utf-8")
        tcp_s.send(data)
        b_data = tcp_s.recv(1024)
        print(b_data.decode("utf-8"))
    tcp_s.close()


main()