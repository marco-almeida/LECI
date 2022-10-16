import sys
import os
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding


usage = "python3 ex01-cipher-mode.py <name of file to encrypt> <name of file to store the cryptogram> <encryption algorithm> <cipher mode>"

if len(sys.argv) != 5:
    print("Error. Execute file like so: " + usage)
    sys.exit()

ALGORITHMS = ['AES', 'ChaCha20']
FILE_TO_ENCRYPT = sys.argv[1]
FILE_TO_STORE_ENCRYPTION = sys.argv[2]
ENCRYPTION_ALGORITHM = sys.argv[3]
CIPHER_MODE = sys.argv[4]
CIPHERS = ['CFB', 'OFB', 'CBC', 'ECB']

if not os.path.isfile(FILE_TO_ENCRYPT):
    print("Error. File %s does not exist." % FILE_TO_ENCRYPT)
    sys.exit()

if ENCRYPTION_ALGORITHM not in ALGORITHMS:
    print("Error. Can only accept these encryption algorithms: %s" % ALGORITHMS)
    sys.exit()

if CIPHER_MODE not in CIPHERS and ENCRYPTION_ALGORITHM == "AES":
    print("Error. Can only accept these cipher modes for AES: %s" % (CIPHERS))
    sys.exit()

key = input("Key (Press enter to randomize key): ")

filIn = open(FILE_TO_ENCRYPT, "r")
plain_text = filIn.read()
filIn.close()

if key == "":
    key = os.urandom(32)
else:  # AES aceita keys de tamanho 128, 192, ou 256 bits. ChaCha20 aceita apenas 256. Então simplificamos e metemos padding de 256 nas duas
    padder_key = padding.PKCS7(256).padder()
    padded_key = padder_key.update(key.encode())
    padded_key += padder_key.finalize()
    key = padded_key

if ENCRYPTION_ALGORITHM == "AES":
    # aes precisa de padding de dados, mas apenas os modos CBC e ECB precisam
    padder = padding.PKCS7(128).padder()
    # dar encode numa variavel é tipo b"string"
    padded_data = padder.update(plain_text.encode())
    padded_data += padder.finalize()    # data with block size as multiple of 128bits
    
    iv = os.urandom(16)
    if CIPHER_MODE == 'CFB':
        cipher = Cipher(algorithms.AES(key), modes.CFB(iv))
    elif CIPHER_MODE == 'OFB':
        cipher = Cipher(algorithms.AES(key), modes.OFB(iv))
    elif CIPHER_MODE == 'ECB':
        cipher = Cipher(algorithms.AES(key), modes.ECB())
    else:
        cipher = Cipher(algorithms.AES(key), modes.CBC(iv))
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
else:   # ChaCha20 nao é block cipher, entao nao precisa de padding nos dados.
    nonce = os.urandom(16)
    algorithm = algorithms.ChaCha20(key, nonce)
    cipher = Cipher(algorithm, mode=None)
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_text.encode())

f = open(FILE_TO_STORE_ENCRYPTION + ".txt", "wb")
f.write(ct)
f.close()

# para dar decrypt é preciso a key como é preciso o iv/nonce acho eu, por isso faço isso ja neste file pa dar menos trabalho

op = input("Do you wish to decrypt the file? (y/n) ").lower()
if op == 'y':
    FILOUT = input("Name of file to store decrypted results: ")
else:
    sys.exit()

decryptor = cipher.decryptor()
if ENCRYPTION_ALGORITHM == "AES":
    data_to_unpad = decryptor.update(ct) + decryptor.finalize()
    
    unpadder = padding.PKCS7(128).unpadder()
    data = unpadder.update(data_to_unpad) + unpadder.finalize()
else:
    data = decryptor.update(ct)

f = open(FILOUT + ".txt", "w")
f.write(data.decode())
f.close()
