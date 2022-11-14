from cryptography import x509
from cryptography.hazmat.backends import default_backend

cert_dic = {}


def get_certificate_dates(certificate):
    dates = (certificate.not_valid_before, certificate.not_valid_after)
    print("Before date:", dates[0], "\nAfter date:", dates[1], "\n")
    return dates


def load_certificate(file_path):
    try:
        with open(file_path, "rb") as reader:
            cert_data = reader.read()

        cert = x509.load_pem_x509_certificate(cert_data, default_backend())

        cert_dic[cert.subject.rfc4514_string()] = cert
    except Exception as e:
        print("Error: ", e, "\n")


def main():
    file_path = input(
        "Write the certificate's path file or write nothing to stop and see all certificate dates: \n > ")
    if len(file_path) == 0:
        return 1

    load_certificate(file_path)

    return 0


if __name__ == '__main__':
    while (True):
        if main() == 1:
            break

    for i in cert_dic:
        get_certificate_dates(cert_dic[i])
