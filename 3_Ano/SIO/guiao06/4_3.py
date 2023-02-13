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
            print("Certificate ", cert.subject.rfc4514_string(), " is valid!")
        else:
            print("Certificate ", cert.subject.rfc4514_string(), " has expired!")

    except Exception as e:
        print("Error: ", e, "\n")


def main():
    all_files = os.scandir("/etc/ssl/certs")
    for f in all_files:
        load_certificate(f)


if __name__ == '__main__':
    main()
