# BD: Guião 7


## ​7.2 
 
### *a)*

```
A relação está na forma 1FN porque tem Dependências Parciais e Dependências Transitivas.
Dependencias Parciais:
Nome_Autor -> Afiliação_Autor

Dependências Transistivas:
Editor -> Endereço_Editor
Tipo_Livro, NoPaginas -> Preço
```

### *b)* 

```
Decomposição de Dependencias Parciais:
EP1(<u>Titulo_Livro</u>, <u>Nome_Autor</u> , Tipo_Livro, Preço, NoPaginas, Editor, Endereço_Editor, Ano_Publicaçao)
EP2(<u>Nome_Autor</u>, Afiliação_Autor)

Decomposição de Dependencias Transitivas:
ED1(<u>Editor</u>, Endereço_Editor)
ED2(<u>Tipo_Livro, <u>NoPAginas</u>, Preço)

Decomposição Final:
EP1(<u>Titulo_Livro</u>, <u>Nome_Autor</u> ,Editor, Tipo_Livro, NoPaginas, Ano_Publicaçao)
EP2(<u>Nome_Autor</u>, Afiliação_Autor)
ED1(<u>Editor</u>, Endereço_Editor)
ED2(<u>Tipo_Livro, <u>NoPAginas</u>, Preço)

```




## ​7.3
 
### *a)*

```
Chave de R: (A, B)
```


### *b)* 

```
F2N:

EP1(<u>A</u>, <u>B</u>, C)
EP2(<u>A</u> , D, E, I, J)
EP3(<u>B</u> , F, G, H)
```


### *c)* 

```
F3N:

ED1(<u>A</u>, <u>B</u>,C)
ED2(<u>A</u>, D, E)
ED3(<u>B</u>, F)
ED4(<u>F</u>, G, H)
ED5(<u>D</u>, I, J)
```


## ​7.4
 
### *a)*

```
Chave de R: (B,C)
```


### *b)* 

```
3FN:

R1(<u>A</u>, <u>B</u>, C, D)
R2(<u>D</u>, E)
```


### *c)* 

```
BCNF:

R1(<u>B</u>, <u>C</u>, D)
R2(<u>C</u>, A)
R3(<u>D</u>, E)
```



## ​7.5
 
### *a)*

```
Chave de R: (A,B)
```

### *b)* 

```
2FN:

R1(<u>A</u>, <u>B</u>, D, E)
R2(<u>A</u>, C)
```


### *c)* 

```
3FN:

R1(<u>A</u>, B, E)
R2(<u>A</u>, C)
R3(<u>C</u>, D)
```

### *d)* 

```
BCFN:

R1(<u>A</u>, B, E)
R2(<u>A</u>, C)
R3(<u>C</u>, D)
```
