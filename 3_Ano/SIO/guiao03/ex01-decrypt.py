# para dar decrypt é preciso a key como é preciso o iv/nonce acho eu, por isso faço isso ja neste file pa dar menos trabalho

import sys
import os
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding
import base64

usage = "python3 ex01-decrypt.py <name of encrypted file> <name of file to store the decrypted results> <encryption algorithm>"

if len(sys.argv) != 4:
    print("Error. Execute file like so: " + usage)
    sys.exit()

FILOUT = sys.argv[2] + ".txt"
ALGORITHMS = ['AES', 'ChaCha20']
ENCRYPTION_ALGORITHM = sys.argv[3]
ENCRYPTED_FILE = sys.argv[1]

f = open("key.txt", "rb")
key = f.read()
f.close()

f = open("iv_nonce.txt", "rb")
iv_nonce = f.read()
f.close()

f = open("result-encrypt.txt", "rb")
padded_data = f.read()
f.close()

if not os.path.isfile(ENCRYPTED_FILE):
    print("Error. File %s does not exist." % ENCRYPTED_FILE)
    sys.exit()

if ENCRYPTION_ALGORITHM not in ALGORITHMS:
    print("Error. Can only accept these encryption algorithms: %s" % ALGORITHMS)
    sys.exit()


if ENCRYPTION_ALGORITHM == "AES":
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv_nonce)) # padding required with cbc
    decryptor = cipher.decryptor()
    data_to_unpad = decryptor.update(padded_data)
    
    unpadder = padding.PKCS7(128).unpadder()
    data = unpadder.update(data_to_unpad) + unpadder.finalize()
else:   # ChaCha20 nao é block cipher, entao nao precisa de padding nos dados.
    algorithm = algorithms.ChaCha20(key, iv_nonce)
    cipher = Cipher(algorithm, mode=None)
    decryptor = cipher.decryptor()
    data = decryptor.update(padded_data)

f = open(FILOUT, "w")
f.write(data.decode())
f.close()
