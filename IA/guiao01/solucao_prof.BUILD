#Exercicio 1.1
def comprimento(lista):
    if lista == []:
        return 0

    return 1 + comprimento(lista[1:])

#Exercicio 1.2
def soma(lista):
    if lista == []:
        return 0

    return lista[0] + soma(lista[1:])

#Exercicio 1.3
def existe(lista, elem):
	pass

#Exercicio 1.4
def concat(l1, l2):
	pass

#Exercicio 1.5
def inverte(lista):
	pass

#Exercicio 1.6
def capicua(lista):
	pass

#Exercicio 1.7
def concat_listas(lista):
    if lista == []:
        return []

    l0 = lista[0]
    lc = lista[1:]

    return l0 + concat_listas(lc)

#Exercicio 1.8
def substitui(lista, original, novo):
	pass

#Exercicio 1.9
def fusao_ordenada(lista1, lista2):
    if lista1 == []:
        return lista2
    if lista2 == []:
        return lista1

    if lista1[0] < lista2[0]:
        return [lista1[0]] + fusao_ordenada(lista1[1:], lista2)
    return [lista2[0]] + fusao_ordenada(lista1, lista2[1:])

#Exercicio 1.10
def lista_subconjuntos(lista):
	pass


#Exercicio 2.1
def separar(lista):
    if lista == []:
        return ([], [])

    a, b = lista[0]

    la, lb = separar(lista[1:])

    return ([a] + la, [b] + lb)

#Exercicio 2.2
def remove_e_conta(lista, elem):
	pass

#Exercicio 3.1
def cabeca(lista):
	pass

#Exercicio 3.2
def cauda(lista):
	pass

#Exercicio 3.3
def juntar(l1, l2):
    if len(l1) != len(l2):
        return None

    if l1 == []:
        return []

    t = l1[0], l2[0]

    l = juntar(l1[1:], l2[1:]) 

    return [t] + l

#Exercicio 3.4
def menor(lista):
	pass

#Exercicio 3.6
def max_min(lista):
	pass
import math

#Exercicio 4.1
impar = lambda x: x % 2 == 1

#Exercicio 4.2
positivo = lambda x: x > 0 

#Exercicio 4.3
comparar_modulo = lambda x, y: abs(x) < abs(y) 

#Exercicio 4.4
cart2pol = lambda x, y: (math.sqrt(x**2 + y**2), math.atan2(y, x))

#Exercicio 4.5
ex5 = lambda f, g, h: lambda x, y, z: h(f(x, y), g(y, z))  

#Exercicio 4.6
def quantificador_universal(lista, f): #all
    if lista == []:
        return True

    if f(lista[0]):
        return quantificador_universal(lista[1:], f)

    return False

#Exercicio 4.7
def quantificador_existencial(lista, f): #any
    if lista == []:
        return False

    if f(lista[0]):
        return True

    return quantificador_existencial(lista[1:], f)

#Exercicio 4.9
def ordem(lista, f):
    if len(lista) == 1:
        return lista[0]

    menor_cauda = ordem(lista[1:], f)

    if f(lista[0], menor_cauda):
        return lista[0]
    return menor_cauda

#Exercicio 4.10
def filtrar_ordem(lista, f):
    pass

#Exercicio 5.2
def ordenar_seleccao(lista, f):
    if lista == []:
        return []

    menor = ordem(lista, f) 

    return [menor] + ordenar_seleccao([e for e in lista if e != menor], f)

 
