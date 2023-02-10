import json
import base64
from cryptography.hazmat.primitives.asymmetric import rsa, padding
from cryptography.hazmat.primitives import serialization, hashes
from cryptography.hazmat.backends import default_backend
from symmetric import *
from hybrid_rsa_aes import HybridCipher


def generate_rsa_key_pair(size=2048):
    '''Returns a tuple with a public key and private key pair, by this order.'''
    # Generate an RSA key pair with a key size of 2048 bits by default
    private_key = rsa.generate_private_key(
        public_exponent=65537, key_size=size, backend=default_backend()
    )

    # Extract the public key from the private key
    public_key = private_key.public_key()

    return public_key, private_key


def public_key_to_pem(public_key):
    '''Takes a public key object and returns a PEM format encoded string.'''
    return public_key.public_bytes(
        encoding=serialization.Encoding.PEM,
        format=serialization.PublicFormat.SubjectPublicKeyInfo
    )


def pem_to_public_key(public_key_pem):
    '''Takes a PEM format encoded string and returns a public key object.'''
    return serialization.load_pem_public_key(
        public_key_pem,
        backend=default_backend()
    )


def encrypt_message(message, public_key):
    '''Takes a message and a public key object and returns an encrypted message.'''
    return public_key.encrypt(
        message,
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None
        )
    )


def decrypt_message(encrypted_message, private_key):
    '''Takes an encrypted message and a private key object and returns a decrypted message.'''
    return private_key.decrypt(
        encrypted_message,
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None
        )
    )


def sign_message(message, private_key):
    '''Takes a message and a private key object and returns a signature.'''
    return private_key.sign(
        message,
        padding.PSS(
            mgf=padding.MGF1(hashes.SHA256()),
            salt_length=padding.PSS.MAX_LENGTH
        ),
        hashes.SHA256()
    )


def verify_signature(message, signature, public_key):
    '''Takes a message, a signature and a public key object and returns True if the signature is valid, False otherwise.'''
    try:
        public_key.verify(
            signature,
            message,
            padding.PSS(
                mgf=padding.MGF1(hashes.SHA256()),
                salt_length=padding.PSS.MAX_LENGTH
            ),
            hashes.SHA256()
        )
        return True
    except:
        return False


def pem_to_str(pem):
    '''Takes a PEM format encoded string and returns a base64 encoded string.'''
    # Base64-encode the PEM data
    base64_data = base64.b64encode(pem)

    # Convert the base64-encoded data to a string
    return base64_data.decode()


def str_to_pem(base64_data):
    '''Takes a base64 encoded string and returns a PEM format encoded string.'''
    # Convert the base64-encoded data to bytes
    pem = base64.b64decode(base64_data)

    return pem


def main():
    '''For example, let's say Alice wants to send an encrypted message to Bob. Alice can use Bob's public key to encrypt the message, and Bob can use his private key to decrypt it. This way, only Bob can read the message, even though Alice used his public key to encrypt it.'''
    (pubkey_alice, privkey_alice) = generate_rsa_key_pair()  # criar obj
    (pubkey_bob, privkey_bob) = generate_rsa_key_pair()  # criar obj
    pubkey_bob = pem_to_str(public_key_to_pem(pubkey_bob))  # converter para pem

    a = ""
    for i in range(1001):
        a += str(i) + " "

    message = {"id": "alice", "message": a}
    message = json.dumps(message).encode("utf-8")  # meter em bytes para assinar

    signature = sign_message(message, privkey_alice)

    message = json.loads(message.decode())  # voltar para dict para adicionar a assinatura
    message['signature'] = signature.hex()

    pubkey_bob = pem_to_public_key(str_to_pem(pubkey_bob))  # converter para pem
    encrypted_message = HybridCipher().encrypt(rsa_public_key=pubkey_bob, data=message)
    print(encrypted_message)
    data = encrypted_message.encode("utf-8")  # pronto a enviar

    # a receber

    data = data.decode("utf-8")  # bytes para string
    decrypted_message = HybridCipher().decrypt(
        rsa_private_key=privkey_bob, cipher_text=data
    )

    signature = bytes.fromhex(decrypted_message['signature'])  # fancy decode

    del decrypted_message['signature']
    pubkey_alice = pem_to_str(public_key_to_pem(pubkey_alice))  # converter key para str
    pubkey_alice = pem_to_public_key(str_to_pem(pubkey_alice))  # converter str para key

    if verify_signature(json.dumps(decrypted_message).encode(), signature, pubkey_alice):
        print("signature verified")
    else:
        print("signature not verified")


if __name__ == "__main__":
    main()
