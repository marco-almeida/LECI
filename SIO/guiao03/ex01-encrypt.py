import sys
import os
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding
import base64

usage = "python3 ex01-encrypt.py <name of file to encrypt> <name of file to store the cryptogram> <encryption algorithm>"

if len(sys.argv) != 4:
    print("Error. Execute file like so: " + usage)
    sys.exit()

ALGORITHMS = ['AES', 'ChaCha20']
FILE_TO_ENCRYPT = sys.argv[1]
FILE_TO_STORE_ENCRYPTION = sys.argv[2]
ENCRYPTION_ALGORITHM = sys.argv[3]

if not os.path.isfile(FILE_TO_ENCRYPT):
    print("Error. File %s does not exist." % FILE_TO_ENCRYPT)
    sys.exit()

if ENCRYPTION_ALGORITHM not in ALGORITHMS:
    print("Error. Can only accept these encryption algorithms: %s" % ALGORITHMS)
    sys.exit()

key = input("Key (Press enter to randomize key): ")

filIn = open(FILE_TO_ENCRYPT, "rb")
plain_text = filIn.read()
filIn.close()

if key == "":
    key = os.urandom(32)
else:  # AES aceita keys de tamanho 128, 192, ou 256 bits. ChaCha20 aceita apenas 256. Então simplificamos e metemos padding de 256 nas duas
    padder_key = padding.PKCS7(256).padder()
    padded_key = padder_key.update(key.encode())
    padded_key += padder_key.finalize()
    key = padded_key

iv_nonce = os.urandom(16)
f = open("iv_nonce.txt", "wb")
f.write(iv_nonce)
f.close()

f = open("key.txt", "wb")
f.write(key)
f.close()

if ENCRYPTION_ALGORITHM == "AES":
    # aes precisa de padding de dados
    padder = padding.PKCS7(128).padder()
    # dar encode numa variavel é tipo b"string"
    padded_data = padder.update(plain_text)
    padded_data += padder.finalize()    # data with block size as multiple of 128bits

    cipher = Cipher(algorithms.AES(key), modes.CBC(iv_nonce))
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
else:   # ChaCha20 nao é block cipher, entao nao precisa de padding nos dados.
    algorithm = algorithms.ChaCha20(key, iv_nonce)
    cipher = Cipher(algorithm, mode=None)
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_text)

f = open(FILE_TO_STORE_ENCRYPTION + ".txt", "wb")
f.write(ct)
f.close()
