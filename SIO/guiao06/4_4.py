from cryptography import x509
from cryptography.hazmat.backends import default_backend
import os
from datetime import datetime

cert_dic = {}


def get_certificate_dates(certificate):
    dates = (certificate.not_valid_before.timestamp(),
             certificate.not_valid_after.timestamp())
    return dates


def validate_certificate(certificate):
    dates = get_certificate_dates(certificate)

    if datetime.now().timestamp() < dates[0] or datetime.now().timestamp() > dates[1]:
        return False

    return True


def load_certificate(file_path):
    try:
        with open(file_path, "rb") as reader:
            cert_data = reader.read()

        cert = x509.load_pem_x509_certificate(cert_data, default_backend())

        if validate_certificate(cert):
            cert_dic[cert.subject.rfc4514_string()] = cert

    except Exception as e:
        print("Error: ", e, "\n")


def get_issuers(certificate, chain=None):
    if chain is None:
        chain = list()
    chain.append(certificate)

    issuer = certificate.issuer.rfc4514_string()
    subject = certificate.subject.rfc4514_string()

    if issuer == subject and subject in cert_dic:
        print("Chain complete")
        return chain

    if issuer in cert_dic:
        return get_issuers(cert_dic[issuer], chain)

    print("Unable to create the Trust Chain")
    return chain


def main():
    all_files = os.scandir("/etc/ssl/certs")
    for f in all_files:
        load_certificate(f)

    for cert in cert_dic:
        chain = get_issuers(cert)

        print(chain)


if __name__ == '__main__':
    main()
