# Guiao03 - Processes, shared memory, and semaphores - #IPC/02

## Try to understand how shared memory is created and used

    Cria-se memoria partilhada com shmget(sizeof Fifo) e dá se mapping com shmat

## Try to understand why race conditions can appear

    O fifo_unsafe é unsafe porque cria um fifo em memoria partilhada, mas nao faz qualquer serviço de mutex para controlar acessos de processos. Por outras palavras, deixa toda a gente aceder ao mesmo tempo acho eu

## What should be done to solve the problem?

    implementar semaforos

## Try to understand how semaphores are used to avoid both race conditions and busy waiting

    No create(), faz-se o semget de 3 semaforos, ou seja criam-se 3 semaforos, daí os indices de nslots, nitems e access irem de 0 a 2. Se successful, dá return ao set identifier, um inteiro positivo, senão -1.

    depois para cada slot, inicializa-se (up) o semoforo nslots e no fim faz se a inicializaçao de access.

    Na fifo unsafe, não há qualquer tipo de validação a inserir e a remover. Apenas verifica se existem items ou nao.

    (items != slots, items sao indices ocupados/produzidos, slots sao indices vazios/consumidos)

    Na fifo safe, nao verifica se existem items ou nao. Apenas mete os slots available bloqueados e de seguida o acesso. Depois produz um item e desbloqueia o acesso e os items.
    
    A retirar, bloqueia primeiro os items (para ninguem mexer nos items enquanto eu tou a mexer) e depois o access. De seguida, retira um item e desbloqueia o acesso e os slots available.

## Question 2 Designing and implementing a **"simple"** client-server application

Antes o fifo tinha semaphore id, fifoid, cnt, ri, ii e ainda slots que guardam elementos do tipo ITEM. Os elementos ITEM apresentam atributos id e value. Entao cada slot do fifo guardava basicamente um ITEM.

Agora temos que adaptar o fifo. Temos na mesma **semid, ii, ri, cnt e fifoid**. Agora em vez de criarmos uma struct ITEM, criamos apenas um atributo **int slot[10]**. Estes inteiros vao ser o **id de buffers**. Isto vai fazer sentido mais à frente.

Faz-se na mesma as funçoes do fifo create, destroy, in e remove.

Para **criar** fifos é igual, têm que se inicializar o semaforo NSLOTS por cada slot. Depois retornamos o FIFO criado.

Para **destruir**, é igual, mas é um **approach mais estatico**. Mete-se no argumento o endereço do fifo que queremos destruir. Destroi-se a shared memory e depois dá se detach do processo - shared memory.

Para **inserir** um elemento (id do buffer), temos que tambem **especificar em que fifo queremos inserir esse elemento**. Coloca-se na mesma **NSLOTS e ACCESS em down**, insere-se o valor do id do buffer no slot de inserção do fifo especificado e no fim faz-se o **up DE ACCESS E DE ITEMS**.

Para **remover** um elemento, temos que **especificar em que fifo queremos remover o elemento**. Coloca-se **NITEMS e ACCESS em down**, retira-se o valor do id do buffer do slot de remoção do fifo especificado e no fim faz-se o **up DE ACCESS E DE NSLOTS**.

Estas 3 funçoes são mais estaticas do que orientadas a objetos. Deve haver uma forma mais bem pensada de fazer isto.

Pronto, a fifo já está feita, em vez de guardar ids/values guarda ids de buffers. Tem que se fazer a classe buffer agora.

Um buffer tem que ter um **bufferId, semId, char data[size], length**. O atributo data é o que colocamos no buffer, uma string por exemplo. O atributo length é o tamanho da string.

O buffer tem que ter as funçoes create, destroy, clear, write, read, wait_until_solved e set_solved.

O **create** é igual, cria-se a shared memory e dá se mapping. Mete-se o semaforo do buffer em up (**set_solved**), uma vez que está disponivel.

O **destroy** é igual, ainda com um **approach estatico**. Mete-se no argumento o endereço do buffer que queremos destruir. Aqui, primeiro dá-se detach da shared memory do addressing space do processo e só depois é que se destrói a shared memory.
No fifo era ao contrario.

O **write** é como um insert. Pede-se o endereço do buffer em que quero escrever, os dados que quero escrever (string) e o tamanho da string. Primeiro vê-se se o buffer tem espaço para a string. Copia-se para o atributo data do buffer a string atraves do ***memcpy()***. Aumenta o atributo length de acordo com o tamanho da string.

O **read** pede o endereço do buffer e o destino para onde meter o que vou ler. Leio então o que está no buffer e coloco na variavel destino, atraves do ***memcpy()***. Depois dou **clear()** ao buffer.

O **clear** coloca a length a 0 e a data sem dados lá dentro.

O **wait_until_solved** é usado mais depois mas é uma função que leva de argumento o endereço de um buffer. Coloca o semaforo desse buffer em down **DUAS VEZES**(nsei pq) para bloquear o buffer.

O **set_solved** é uma função que leva de argumento o endereço de um buffer. Coloca o semaforo desse buffer em up para sinalizar que está desbloqueado.

Pronto, agora temos a fifo e o buffer. Outra vez, o fifo vai guardar em si os ids dos buffers. O buffer vai guardar dados (string). O buffer pode tar ocupado enquanto leva **read** ou **write**, daí ter semaforos. A fifo também pode levar **insert** ou **remove**.

Agora temos que simular o client-server. Faz-se o service.h para auxilio.

Um serviço tem o tipo de operação, **enum operation** {letters, digits}. É também ou um **request ou um response**.
No caso de ser um request, tem o **enum operation**, **string data**, **int length**.
No caso de ser um response, tem o **int length** e **string data**.

Tem tambem as funçoes **create()**, **destroy()**, **callService(ServiceRequest& req, ServiceResponse& res)** e **processService()**.

Temos então que replicar a seguinte arquitetura: ![Arquitetura](https://i.imgur.com/WjNk07A.png "Arquitetura")

Temos um array de buffers, um fifo de buffers disponiveis e um fifo de buffers ocupados.

Digamos que N é 10, ou seja a nossa pool contém 10 buffers.

Cria-se então estaticamente ***`BUFFER *pool[N]`***, ***`FIFO *freeBuffers`*** e ***`FIFO *pendingRequests`***.

Função **create()**: dá-se vida ao fifo do freeBuffers e ao fifo do pendingRequests.

```cpp
freeBuffers = fifo::create(); // 10 slots in this fifo
pendingRequests = fifo::create(); // 10 slots in this fifo
```

Como estamos no inicio do programa, todos os buffers estão disponiveis. Logo, fazemos o **insert** dos ids de todos os buffers no fifo dos buffers disponiveis.

```cpp
for (i = 0; i < N; i++) {
    fifo::in(*freeBuffers, i); // no fifo freebuffers, adicionar o i
}                              // que vai ser o id de um buffer
```

Os buffers ainda não foram criados em lado nenhum, apenas temos o array de ponteiros para buffers. Vamos criar os buffers agora.

```cpp
for (i = 0; i < N; i++) {
    pool[i] = buffer::create();
}
```

Função **destroy()**: dá-se destroy ao fifo do freeBuffers e ao fifo do pendingRequests. Faz-se for-loop de 0 a N e dá se destroy a cada buffer na pool. `buffer::destroy(*pool[i]);`

Função **callService(ServiceRequest &req, ServiceResponse &res)**: A ideia desta funçao pegar num id de um buffer nos freebuffers, escrever nesse buffer (da pool) (`buffer::write()`) lá dados, dados estes que nos são dados no `&req`. De seguida, inserimos este buffer no fifo de pending requests `fifo::in(*pendingRequests, id)`.

Agora bloqueamos o buffer enquanto uma resposta não chega. `buffer::wait_until_solved(*pool[id]);`
De seguida, escreve-se no `&res` os dados que estão no buffer.

```cpp
res.data = (char *)malloc(sizeof(char) * pool[id]->length); // recebe o que estava no buffer. É preciso alocar a memoria exata para meter a string na variavel que nao sei quanto ocupa.
res.size = pool[id]->length;
buffer::read(*pool[id], res.data);
```

Função **static void produceResponse(ServiceRequest &req, ServiceResponse &res)**: Esta função tem o objetivo de processar a mensagem. Vamos analisar, ou quantos digitos a mensagem tem, ou quantas letras.

De acordo com o tipo de operação em `&req`, vamos fazer o que temos de fazer. Se for **letters**, vamos contar quantas letras tem a mensagem. Se for **digits**, vamos contar quantas letras tem a mensagem.

Metemos o resultado na resposta `res.data`

Função **processService()**: Esta função vai pegar num pending buffer e vai processar a mensagem.

Primeiro vai ao fifo de pendingrequests buscar um id.
Faz um objeto ServiceRequest e nos atributos deste (data e size), lê da pool o que está no buffer (através do id previamente obtido).

```cpp
int id;
fifo::out(*pendingRequests, id);  // get id

ServiceRequest req;
req.data = (char *)malloc((pool[id]->length) * sizeof(char));
req.size = (pool[id]->length) * sizeof(char);
buffer::read(*pool[id], req.data);
```

Para acabar de preencher os atributos do ServiceRequest, falta ver o tipo de operaçao. Vamos ver o primeiro caracter da mensagem. Se for um digito, é digits. Se for uma letra, é letters. (ig idk)

Criamos agora um objeto ServiceResponse.
Chamamos o **produceResponse(req, res)** para preencher o `res`. De seguida dá-se free ao req.data, clear ao buffer, write no buffer da resposta, free da res.data e por fim, fazemos o set_solved ao buffer para o semaforo sinalizar que o buffer ja está disponivel. Acho que agora se podia colocar o buffer no fifo de freebuffers e remover do pendingrequests.

Já ta tudo feito, agora é fazer o main para correr isto tudo.

Faz-se uma função que gera random requests: **ServiceRequest get_random_request()**
Cria-se um objeto ServiceRequest e preenche-se os atributos. O tipo de operação é random. A string tambem. Dá return ao request.

Faz-se uma função que produz e recebe resposta: **void produce_and_receive_response()**: Cria-se e preenche-se um objeto de request a partir da função get_random_request. Depois printa-se o que o cliente mandou (o request). Depois cria-se um objeto de response e chama-se a função callService(request, response). Por fim, printa-se a resposta e dá se free aos dados.

Função de consume() que chama o processService().

Por fim a main. Declaramos 4 consumers e 4 producers. Faz-se create() do service. Faz-se for-loop de 0 a 4 e cria-se os consumers com fork. Os filhos fazem consume() e exit(0). Faz-se depois for-loop de 0 a 4 e cria-se os producers com fork. Os filhos fazem produce_and_receive_response() e exit(0).

Faz-se for loop pwaitpid() dos consumers e dos producers, ou seja espera-se pelo término de cada processo. No fim dá se destroy ao processo. Acabou.