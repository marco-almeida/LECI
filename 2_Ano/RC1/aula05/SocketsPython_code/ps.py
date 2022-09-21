import socket
import threading
import signal
import sys
def signal_handler(sig, frame):
    print('\nDone!')
    sys.exit(0)
print("∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗\n∗ Server Configuration ∗\n∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗")
interval = input("\nInterval of time (seconds) to get notified on the clients' CPU and RAM usage: ")
while (not interval.isnumeric()):
    interval = input("Error: Input! Please input value of integer type: ")
print(f"Server set up to receive CPU and RAM updates every {interval} seconds.")
signal.signal(signal.SIGINT, signal_handler)
print('Press Ctrl+C to exit...')
numConnections = 0
clients = []
    
##

def handle_client_connection(client_socket,address):
    client_connect_msg(address)
    client_socket.send(interval.encode())
    try:
        while True:
            request = client_socket.recv(1024)
            if not request:
                client_socket.close()
            else:
                msg=request.decode()
                print('Received {}'.format(msg))
                msg=("ECHO: "+msg).encode()
                client_socket.send(msg)
    except (socket.timeout, socket.error):
        client_quit_msg(address)           # nao funcemina

ip_addr = "0.0.0.0"
tcp_port = 5005

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((ip_addr, tcp_port))
server.listen(5)  # max backlog of connections

print('Listening on {}:{}'.format(ip_addr, tcp_port))

def client_quit_msg(address):
    clients.remove(address)
    print('Client {} has left.'.format(address))
    print(f"Number of total connections: {numConnections}")
    print(f"Number of current connections: {len(clients)}")

def client_connect_msg(address):
    print('Accepted connection from {}:{}'.format(address[0], address[1]))
    print(f"Number of current connections: {len(clients)}")

while True:
    client_sock, address = server.accept()
    numConnections += 1
    clients.append(address)
    client_handler = threading.Thread(target=handle_client_connection,args=(client_sock,address),daemon=True)
    client_handler.start()

