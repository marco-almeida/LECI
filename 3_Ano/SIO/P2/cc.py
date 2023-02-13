from PyKCS11 import *
from cryptography.hazmat.backends import default_backend
from cryptography import x509
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives.serialization import Encoding
from cryptography.hazmat.primitives import hashes
from cryptography.exceptions import *
from cryptography import x509
import base64


def initPkcs():
    lib = '/usr/local/lib/libpteidpkcs11.so'
    try:
        pkcs11 = PyKCS11Lib()
        pkcs11.load(lib)
    except PyKCS11Error:
        Exception("Error loading the lib")
        exit(10)
    else:
        try:
            # listing all card slots
            slots = pkcs11.getSlotList()
            if len(slots) < 1:
                print("\nERROR: No card reader detected!")
                raise Exception()
            return pkcs11.openSession(slots[0])
        except PyKCS11Error:
            print("Couldn't open the session")
            exit(10)
        except:
            print("No card detected!")
            exit(11)


def login():
    pin = None
    while True:
        pin = input('PIN: ')
        try:
            initPkcs().login(pin)
        except PyKCS11Error:
            print("\nERROR: Pin Incorrect")
            return False
        else:
            return True


def getName():
    info = initPkcs().findObjects(template=([(PyKCS11.CKA_LABEL, "CITIZEN AUTHENTICATION CERTIFICATE"),
                                             (PyKCS11.CKA_CLASS, PyKCS11.CKO_CERTIFICATE)]))

    infos = ''.join(chr(c)
                    for c in [c.to_dict()['CKA_SUBJECT'] for c in info][0])

    names = infos.split("BI")[1].split("\x0c")
    return ' '.join(names[i] for i in range(1, len(names)))


def getCerts():
    info = initPkcs().findObjects(template=(
        [(PyKCS11.CKA_CLASS, PyKCS11.CKO_CERTIFICATE), (PyKCS11.CKA_LABEL, "CITIZEN AUTHENTICATION CERTIFICATE")]))
    der = bytes([c.to_dict()['CKA_VALUE'] for c in info][0])
    # converting DER format to x509 certificate
    cert = x509.load_der_x509_certificate(
        der, default_backend()).public_bytes(Encoding.PEM)
    return cert


def cert_to_string(cert):
    # Encode the certificate in base64 format
    encoded_cert = base64.b64encode(cert).decode('utf-8')
    return encoded_cert


def cert_from_string(encoded_cert):
    # Decode the base64 encoded certificate
    cert = base64.b64decode(encoded_cert)
    return cert


def signCC(message):
    privateKey = initPkcs().findObjects(template=(
        [(PyKCS11.CKA_CLASS, PyKCS11.CKO_PRIVATE_KEY), (PyKCS11.CKA_LABEL, "CITIZEN AUTHENTICATION KEY")]))[0]
    signedlist = initPkcs().sign(privateKey, message,
                                 Mechanism(PyKCS11.CKM_SHA256_RSA_PKCS, ""))
    return bytes(signedlist)


def verifySign(cert, data, signature):
    cert = x509.load_pem_x509_certificate(cert, default_backend())
    publicKey = cert.public_key()
    paddingv = padding.PKCS1v15()
    try:
        state = publicKey.verify(
            signature,
            bytes(data.encode()),
            paddingv,
            hashes.SHA256(),
        )
        return True
    except:
        return False


if __name__ == '__main__':

    print(getName())

    cert = getCerts()
    print('cert: ', cert)
    # Check that the returned value is a bytes object
    assert isinstance(cert, bytes)
    # print(cert)
    # Check that the returned value is a valid PEM-formatted certificate
    try:
        x509.load_pem_x509_certificate(cert, default_backend())
    except Exception as e:
        # If the certificate is invalid, the load_pem_x509_certificate function will raise an exception
        assert False, f"getCerts() returned an invalid certificate: {e}"

    datatobeSigned = "exemplo"
    print("Texto a encriptar: ", datatobeSigned)
    signedData = signCC(datatobeSigned)
    print("Texto encriptado: ", signedData)
    print("Sign Test->")
    if (verifySign(cert, datatobeSigned, signedData)):
        print("verified!")
