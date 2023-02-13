from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
import binascii
import getpass
import os

salt = os.urandom(16)

kdf = PBKDF2HMAC(
    algorithm=hashes.sha256(),
    length=32,  # random
    salt=salt,
    iterations=1e4  # random
)

password = getpass("Password: ")
data = kdf.derive(password)
iv = data[:16]
key = data[16:]

print(password, binascii.hexlify(data), binascii.hexlify(iv), binascii.hexlify(key))
