# Aula01 - Notes

Realizado por:
- Diogo Falcão (Nºmec 108712)
- José Gameiro (Nºmec 108840)

## Exercício 1
### Objetivo

Este exercício tem como objetivo, ao receber como argumento um ficheiro de texto que contém uma sopa de letras por resolver e um conjunto de palavras que se encontram nela, resolver a sopa de letras e depois imprimir a sopa de letras resolvida com a informação de cada palavra que tem o seguinte formato:
palavra           tamanho da palavra(lenght)          linha da primeira letra,coluna da primeira letra       direção da palavra<br />
Esta informação está presente nos ficheiros:
- sopa01_solution.txt
- sopa02_solution.txt
- sopa03_solution.txt

### Intruções de compilação e execução

Para compilar é necessário executar o comando na pasta lab01:
```
javac WSSolver.java Direction.java Validate.java Solver.java GetData.java
```
Para executar basta executar o seguinte comando, passando como argumeno um dos seguintes ficheiros:
- sopa01.txt
- sopa02.txt
- sopa03.txt <br />

Comando:
```
java WSSolver Outputs/sopa01.txt
```

## Exercício 2
### Objetivo
O objetivo deste exercício é gerar uma sopa de letras que contenham um conjunto de palavras que estão presentes num ficheiro de texto passado como argumento, bem como o número de linhas que a sopa de letras deverá de ter. Os resultados da nossa implementação podem ser consultados nos ficheiros:
- wordlist_result1.txt
- wordlist_result2.txt
- wordlist_result3.txt

### Instruções de compilação e execução
Para compilar é necessário executar ir à pasta lab01 e executar o seguinte comando:
```
javac WSGenerator.java Direction.java
```
Para executar basta executar o seguinte comando que tem como argumentos obrigatórios o nome do ficheiro que tem as palavras e um número inteiro que será o número de linhas a sopa de letras vai ter, também se pode passar como argumento o nome do ficheiro de texto em que queira guardar, mas caso não se faça irá ter como nome wordlist_result.txt. Para o ficheiro de texto que contém as palavras pode usar os seguintes:
- wl01.txt
- wl02.txt
- wl03.txt<br />

Comando:
```
java WSGenerator -i Outputs/wl01.txt -s 13
java WSGenerator -i Outputs/wl01.txt -s 13 -o wordlist_result1.txt
```

