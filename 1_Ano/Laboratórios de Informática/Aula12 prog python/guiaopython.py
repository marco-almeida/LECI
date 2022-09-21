import os.path
import sys
import socket

# def calculator(num1, operador, num2):
#    if (operador == "+"):
#       return num1 + num2
#  elif (operador == "*"):
#     return num1 * num2
# elif (operador == "-"):
#   return num1 - num2
# elif (operador == "/"):
#    if (num2 == 0):
#       return = "Can't divide by 0"
#  else:
#     return num1 / num2

#print(calculator(int(input("Primeiro Número: ")), input("Operador: "), int(input("Segundo Número: "))))

#print(len("t af od4 er 1 23".split(" "))-1)
#a = 0
# for i in frase:
#    if i == " ":
#        a = a +1
#frase = "taf od4 er 1 23"
#fraseReversed = ""
# for i in frase:
#    fraseReversed = i + fraseReversed  # appending chars in reverse order

#i = len(frase)-1
# while (i >= 0):
#    fraseReversed = fraseReversed + frase[i]
#   i = i - 1
# print(fraseReversed)
#num = int(input("Numero para fazer fatorial: "))
#i = 1
#sum = 1
# while (i <= num):
#    sum = sum * i
#    i = i + 1
# print(sum)

# def multiplos(a, b):

#    while (a <= b):
#        if (a % 3 == 0):
#            print(a)
#        a = a + 1

#multiplos(int(input("A: ")), int(input("B: ")))

# 33
# fname = "C:/Users/marco/Desktop/uni/Labi/Python/textopy.txt"
# if not os.path.exists(fname):
#     sys.exit("Não existe")
# if os.path.isdir(fname):
#     sys.exit("É diretório")
# if not os.path.isfile(fname):
#     sys.exit("Não é ficheiro")
# f = open(fname,"r")
# f = open(fname, "r")
# conteudo = ""
# while True:
#     linha = f.readline()
#     conteudo = conteudo + linha
#     if linha == "":
#         break
# f.close()
# palavras = len(conteudo.split(" "))-1 + len(conteudo.split("\n"))
# carateres = len(conteudo)
# linhas = len(conteudo.split("\n"))
# if (carateres == 0):
#    palavras = 0
# print("Número de carateres: %d\nNúmero de palavras: %d\nNúmero de linhas: %d" % (carateres, palavras, linhas))

# f = open("C:/Users/marco/Desktop/uni/Labi/Python/textopy.txt", "r")

# conteudo = ""

# while True:
#     linha = f.readline()
#     conteudo = conteudo + linha
#     if linha == "":
#         break
# f.close()
# print(conteudo)

# i = len(conteudo) - 1
# while (i >= 0):
#     print(conteudo[i], end ="")
#     i = i - 1

# fname = "C:/Users/marco/Desktop/uni/Labi/Python/textopy.txt"
# if not os.path.exists(fname):
#     sys.exit("Não existe")
# if os.path.isdir(fname):
#     sys.exit("É diretório")
# if not os.path.isfile(fname):
#     sys.exit("Não é ficheiro")
# f = open(fname,"r")

# frase = input("Frase to reverse")

# i = len(frase)-1
# while (i >= 0):
#     print(frase[i], end = "")
#     i= i - 1    

# import socket

# def main():
#     udp_s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
#     udp_s.bind( ("127.0.0.1", 1234) )

#     while 1:
#         b_data, addr = udp_s.recvfrom(4096)

#         if b_data.lower() == "quit":
#             break

#         udp_s.sendto(b_data.upper(), addr)
    
#     udp_s.close()

# if __name__ == "__main__":
#     main()

def isEven(a) :
    if a % 2 == 0:
       return True
    else:
        return False


def main() :
   page = int(input("Enter page number: "))
   if isEven(page):
      print(page)
   else :
      print("%60d" % page)

  
main() 