from cryptography.hazmat.primitives import hashes
import os
import sys
import binascii

HASHES = ["MD5", "SHA256", "SHA384", "SHA512", "Blake2"]
USAGE = "python3 digest.py <file> <hash>"

if sys.argv[2] not in HASHES:
    print(f"Wrong hash. Try with these: {HASHES}")
    sys.exit()


def calculate(fname):
    HASHES_DICT = {"MD5": hashes.Hash(hashes.MD5()), "SHA256": hashes.Hash(hashes.SHA256()), "SHA384": hashes.Hash(
        hashes.SHA384()), "SHA512": hashes.Hash(hashes.SHA512()), "Blake2": hashes.Hash(hashes.BLAKE2b(64))}
    digest = HASHES_DICT[sys.argv[2]]

    with open(fname, 'rb') as f:
        while True:
            data = f.read(1024*1024)
            if len(data) == 0:
                break
            digest.update(data)
    return digest.finalize()


hd = binascii.hexlify(calculate(sys.argv[1]))
print(f"{hd.decode()}: {sys.argv[1]}")
