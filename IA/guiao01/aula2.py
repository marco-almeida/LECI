import math
import functools
#Exercicio 4.1
impar = lambda x : x % 2 != 0

#Exercicio 4.2
positivo = lambda x : x > 0

#Exercicio 4.3
comparar_modulo = lambda x, y : abs(x) < abs (y)

#Exercicio 4.4
cart2pol = lambda x, y : (math.sqrt(x**2 + y**2), math.atan2(y,x))

#Exercicio 4.5
ex5 = lambda f,g,h : lambda x,y,z : h(f(x,y), g(y,z))

#Exercicio 4.6
def quantificador_universal(lista, f):
    return filter(f, lista)
    

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
    if not lista:
        return 0, []

    s1, s2 = filtrar_ordem(lista[1:], f)

    if f(lista[0], s1):
        return lista[0], s2
    
    return s1, [lista[0]] + s2

#Exercicio 5.2
def ordenar_seleccao(lista, ordem):
    if len(lista) <= 1:
        return lista
    else:
        return ordenar_seleccao([e for e in lista[1:] if ordem(e, lista[0])], ordem) + [lista[0]] +\
            ordenar_seleccao([e for e in lista[1:] if not ordem(e, lista[0])], ordem)
    
