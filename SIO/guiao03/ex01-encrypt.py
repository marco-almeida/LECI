from cryptography.fernet import Fernet
message = "my deep dark secret".encode()  # .encode() is used to turn the string to bytes
key = Fernet.generate_key()  # Store this key or get if you already have it
f = Fernet(key)
encrypted = f.encrypt(message)
decrypted = f.decrypt(encrypted)
#message == decrypted  # The original message and decrypted bytes are the same