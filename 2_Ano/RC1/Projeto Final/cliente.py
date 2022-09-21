import psutil
import socket
import signal
import sys

def signal_handler(sig, frame):
    print('\nDisconnected!')
    sys.exit(0)

signal.signal(signal.SIGINT, signal_handler)
print('Press Ctrl+C to quit...')

##

ip_addr = "203.0.0.129"
tcp_port = 5005

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

sock.connect((ip_addr, tcp_port))
response = sock.recv(4096).decode()
print(f"Connected to {ip_addr}:{tcp_port}\nTransmitting CPU and RAM Usage every {response} seconds.")
while True:
    try: 
        message = str(psutil.cpu_percent(int(response))) + " " + str((psutil.virtual_memory()[2]))
        sock.send(message.encode())
    except (socket.timeout, socket.error):
        print('\nServer error. Disconnecting!')
        sys.exit(0)

