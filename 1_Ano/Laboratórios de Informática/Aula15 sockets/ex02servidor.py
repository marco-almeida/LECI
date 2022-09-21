# server
import socket


def main():
    udp_s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    udp_s.bind(("127.0.0.1", 0))
    server_addr = ("127.0.0.1", 1234)

    while 1:
        frase = input("<-:")
        b_data = frase.encode("utf-8")
        udp_s.sendto(b_data, server_addr)
        # ---
        b_data, addr = udp_s.recvfrom(4096)
        frase = b_data.decode("utf-8")
        print("->:%s \n" % frase)


main()
