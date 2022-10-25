import sys
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives import hashes
import os

USAGE = "python3 encrypt-rsa.py <file_to_encrypt> <public_key_file> <encrypted_file_name>"

if len(sys.argv) != 4:
    print("Wrong number of arguments! \nExecute in format: " + USAGE)
    sys.exit()

if not os.path.isfile(sys.argv[1]):
    print("File to encrypt does not exist!")
    sys.exit()

FILE_TO_ENCRYPT = sys.argv[1]
PUBLIC_KEY_FILE = sys.argv[2]
ENCRYPTED_FILE_NAME = sys.argv[3]

with open(PUBLIC_KEY_FILE, "rb") as key_file: # ler public key
    public_key = serialization.load_pem_public_key(
        key_file.read(),
    )


with open(ENCRYPTED_FILE_NAME + ".txt", "wb") as f:
    f.write("".encode())


increment = (public_key.key_size / 8)
with open(FILE_TO_ENCRYPT, "rb") as f:
    while f.read(int(increment - 11)):
        ciphertext = public_key.encrypt(
        f.read(int(increment - 11)),
        padding.PKCS1v15()
        )

    with open(ENCRYPTED_FILE_NAME + ".txt", "ab") as f:
        f.write(ciphertext)
    increment += (public_key.key_size / 8)

