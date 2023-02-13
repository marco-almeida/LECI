import os
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives.padding import PKCS7
from cryptography.hazmat.backends import default_backend
from utils import *
import random


def encrypt_sym_deck(plaintext: bytes, key: bytes):
    # Create a Cipher object using the key and the specified algorithm and mode
    # Generate a random initialization vector
    iv = os.urandom(16)
    # Create the AES cipher object
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv), backend=default_backend())
    # Use the Cipher object as an encryptor
    encryptor = cipher.encryptor()
    # A number might be small in size, but we must use padding to make sure that the data is a multiple of the block size
    padder = PKCS7(128).padder()
    padded_data = padder.update(plaintext) + padder.finalize()
    # Encrypt the padded data
    ciphertext = encryptor.update(padded_data) + encryptor.finalize()
    return iv + ciphertext


def decrypt_sym_deck(ciphertext: bytes, key: bytes):
    # Create a Cipher object using the key, the specified algorithm and mode, and an empty IV
    iv = ciphertext[:16]
    ciphertext = ciphertext[16:]
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv), backend=default_backend())
    # Use the Cipher object as a decryptor
    decryptor = cipher.decryptor()
    # Decrypt the encrypted data
    padded_data = decryptor.update(ciphertext) + decryptor.finalize()
    # Unpad the decrypted data
    unpadder = PKCS7(128).unpadder()
    plaintext = unpadder.update(padded_data) + unpadder.finalize()
    # Print out the plaintext after unpadding
    return plaintext


def generate_symmetric_key():
    return os.urandom(16)  # 16 bytes = 128 bits


def main():
    # Test the key generation function
    key1 = generate_symmetric_key()
    deck = generate_deck(16)
    print(deck)

    # encrypt deck
    deck = [str(x).encode() for x in deck]
    encrypted_deck1 = [encrypt_sym_deck(x, key1) for x in deck]
    random.shuffle(encrypted_deck1)

    # encrypt each number again
    key2 = generate_symmetric_key()
    encrypted_deck2 = [encrypt_sym_deck(x, key2) for x in encrypted_deck1]
    random.shuffle(encrypted_deck2)

    # now decrypt everything in reverse order in order to reach original deck
    decrypted_deck2 = [decrypt_sym_deck(x, key2) for x in encrypted_deck2]
    decrypted_deck1 = [decrypt_sym_deck(x, key1) for x in decrypted_deck2]
    print(decrypted_deck1)


if __name__ == "__main__":
    main()
