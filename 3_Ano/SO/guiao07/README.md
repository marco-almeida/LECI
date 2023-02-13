# guiao 04 bash

0>z quer dizer que redireciona o stdin
1>z OU >z quer dizer que redireciona o stdout
2>z quer dizer que redireciona o stderr

`ls a*` é mostrar tudo que começa com a

`ls *1` é mostrar tudo que termina com 1

`ls a?` é mostrar tudo que começa com a e tem 1 caracter depois

`ls b??` é mostrar tudo que começa com b e tem 2 caracteres depois

`ls b?*` é mostrar tudo que começa com b e tem 1 ou mais caracteres depois

`ls *` é mostrar tudo

`ls [ac]` é mostrar tudo que começa com a ou c

`ls [a-c]` é mostrar tudo que começa com a, b ou c

`ls [a-c]?` é mostrar tudo que começa com a, b ou c e tem 1 caracter depois

`ls [ab]*` é mostrar tudo que começa com a ou b e tem 1 ou mais caracteres depois

`/` mete-se atras de um carater especial se nao quisermos usar a especialidade dele

`echo x${x}` vai printar x e o valor da variavel x. Quando nao ha ambiguidade, pode-se usar so $x

```bash
v=a* # a* is not expanded in assignment
echo $v
echo "$v"
echo '$v'
```

Isto vai printar o valor de a*, ou seja, as cenas todas que começam com 'a' e têm mais carateres. Depois vai printar a*, e por fim vai printar $v

A diferença entre agrupar comandos com {} e com () é que o segundo executa numa nova instancia da bash. Por exemplo, variaveis criadas dentro do () sao tipo variaveis criadas num if. fora de lá nao existem.

`echo $?` depois de um comando, ls, por exemplo, vai printar o exit status do comando. 0 se correu bem, 1 se correu mal.
