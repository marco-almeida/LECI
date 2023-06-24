# Tema **ADV**, grupo **adv-06**

-----

## Constituição dos grupos e participação individual global

| NMec | Nome | email | Participação |
|:---:|:---|:---|:---:|
| 108269 | ANDRÉ MIGUEL ANTUNES CARDOSO | <andremacardoso@ua.pt> | 13.4% |
| 104341 | DIOGO BRANCO ALVES DA SILVA | <diogobranco.as@ua.pt> | 13.3% |
|  93096 | JOÃO FRANCISCO TEIXEIRA CATARINO | <jftcatarino@ua.pt> | 5.0% |
| 103440 | MARCO ANTONIO ALVES ALMEIDA | <marco.almeida@ua.pt> | 55.0% |
| 107263 | TIAGO ALEXANDRE ABREU FIGUEIREDO | <tiago.a.figueiredo@ua.pt> | 13.3% |

## Estrutura do repositório

- **src** - deve conter todo o código fonte do projeto.

- **doc** -- deve conter toda a documentação adicional a este README.

- **examples** -- deve conter os exemplos ilustrativos das linguagens criadas.

  - Estes exemplos devem conter comentários (no formato aceite pelas linguagens),
      que os tornem auto-explicativos.

## Relatório

Acreditamos que todos os requisitos mínimos foram alcançados.

### Additional libraries setup

`pip install opencv-python`

### Alfabeto

É apenas permitida a criação de um alfabeto por ficheiro.

### Autómato

Todos os automatos sao verificados semanticamente para ver se cumprem as regras DFA e complete DFA.


É verificado semanticamente se existe um e apenas um estado inicial.

### Vistas

É possível redefinir transições, através de `points`.


É possível alinhar labels, de acordo com `points` e também com `align`.


É possível criar grids.


É verificado semanticamete se todos os estados declarados sao usados com o `place .. at`.


### Animações e Viewports

Um viewport cria uma janela de acordo com o tamanho especificado, é possível ter varios viewports numa animação, e várias vistas podem ser utilizadas.

### Play

Apenas é possível ter um play por ficheiro.

### Valores key-value pair

Grids, estados e labels podem possuir pares chave-valor.

### Outros

Estados, transicoes e outras variáveis sao sempre verificadas semanticamente para evitar a insercao de dados que nao existem

## Contribuições

- Marco Almeida

  - Segunda gramática, sendo esta a que está a ser utilizada de momento (100%);
  - Testes e criação da estrutura do código gerado em python (100%);
  - Geração de código (100%);
  - Exemplos adicionais (90%);
  - Relatório (50%);

- André Cardoso

  - Análise semântica (33,4%);

- Tiago Figueiredo

  - Análise semântica (33,3%);

- Diogo Silva

  - Análise semântica (33,3%);
  - Exemplos adicionais (10%);
  - Relatório (50%);

- João Catarino

  - Primeira versão da gramática (100%);
