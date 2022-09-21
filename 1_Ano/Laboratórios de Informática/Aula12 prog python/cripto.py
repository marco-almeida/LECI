import hashlib
import os
import sys
from random import seed
from random import randint
print("Welcome to Guess game")
value = randint(0, 100)
tries = 0

while (True):
    a = int(input("Guess the number: "))
    tries += 1
    if (a > value):
        print("Too high, try again!")
    elif (a < value):
        print("Too low, try again!")
    else:
        print("Congratulations, you just won with " + str(tries) + " tries")
        break

# h = hashlib.md5() # Iniciar contexto
# h.update("A long sentence ".encode("utf-8") ) # Adicionar dados
# h.update("broken in two halves".encode("utf-8") ) # Adicionar mais dados
# print(h.hexdigest()) # Calcular síntese

# f = open()
# b = hashlib.sha1()
# b.update("ta foder".encode("utf-8"))
# print(b.hexdigest())

# fname = "C:/Users/marco/Desktop/uni/Labi/Python/textocripto.txt"
# tf = True
# if not os.path.exists(fname):
#     tf = False
#     sys.exit("Não existe")
# if os.path.isdir(fname):
#     tf = False
#     sys.exit("É diretório")
# if not os.path.isfile(fname):
#     tf = False
#     sys.exit("Não é ficheiro")
# if (tf):
#     f = open(fname, "rb")
#     buffer = f.read(512)

#     conteudo = ""
#     while True:
#         linha = f.readline()
#         conteudo = conteudo + linha
#         buffer = f.read(512)
#         if linha == "":
#             break
#     f.close()

# from Crypto.Hash import MD5

# h = MD5.new()
# h.update("A long sentence ".encode("utf-8"))
# h.update("broken in two halves".encode("utf-8"))
# print(h.hexdigest())
