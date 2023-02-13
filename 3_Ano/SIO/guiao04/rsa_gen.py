import sys
from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.primitives import serialization

USAGE = "python3 rsa_gen.py <key length> <private_key_file> <public_key_file>"
ALLOWED_KEY_LENGTHS = ['1024', '2048', '3072', '4096']

if len(sys.argv) != 4:
    print("Wrong number of arguments! \nExecute in format: " + USAGE)
    sys.exit()

if sys.argv[1] not in ALLOWED_KEY_LENGTHS:
    print("Key lenght must be one of these " + ALLOWED_KEY_LENGTHS)
    sys.exit()

KEY_LENGTH = int(sys.argv[1])
PRIVATE_KEY_FILE = sys.argv[2]
PUBLIC_KEY_FILE = sys.argv[3]


private_key = rsa.generate_private_key( # gerar private key
    public_exponent=65537,
    key_size=KEY_LENGTH,
)
public_key = private_key.public_key() # gerar public key a partir da private key

with open(PRIVATE_KEY_FILE + ".key", "wb") as f: # store private key em formato .key (convençao)
    f.write(private_key.private_bytes(
        encoding=serialization.Encoding.PEM,
        format=serialization.PrivateFormat.TraditionalOpenSSL,
        encryption_algorithm=serialization.NoEncryption(),
    ))

with open(PUBLIC_KEY_FILE + ".pem", "wb") as f: # store public key em format .pem
    f.write(public_key.public_bytes(
        encoding=serialization.Encoding.PEM,
        format=serialization.PublicFormat.SubjectPublicKeyInfo,
    ))
