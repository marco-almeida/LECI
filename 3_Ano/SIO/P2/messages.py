def send_msg(dst, msg):
    # 4-byte integer, network byte order (Big Endian)
    length = len(msg).to_bytes(4, 'big')
    dst.send(length)
    dst.send(msg)


def exact_recv(src, length):
    data = bytearray(0)

    while len(data) != length:
        more_data = src.recv(length - len(data))
        if len(more_data) == 0:  # End-of-File
            return None
        data.extend(more_data)
    return data


def recv_msg(src):
    # 4-byte integer, network byte order (Big Endian)
    data = exact_recv(src, 4)
    if data == None:
        return None

    length = int.from_bytes(data, 'big')
    return exact_recv(src, length)
