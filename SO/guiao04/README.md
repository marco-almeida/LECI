# Threads

## Try to understand how the shared FIFO is created

    Como o guiao anterior, a fifo tem as mesmas cenas, mas não se cria shared memory tão explicitamente (shmget, shmat), agora faz-se apenas new. A destruir fifos, também só é fazer delete fifo e fifo = NULL e já tá, antes era preciso fazer shmctl e shmdt. Nesta versão, também não é preciso fifoId.

## Try to understand why race conditions can appear

    Tal como no guiao anterior, não há mutex a aceder à memoria partilhada. Então diferentes processos podem fazer alterações de modo dessincronizado.

## What should be done to solve the problem

    Mutex.

## Try to understand how mutexes and condition variables are used to avoid both race conditions and busy waiting

- Diferenças logo depois de declarar a struct do fifo:

```cpp
static FIFO *fifo = NULL;
static pthread_mutex_t accessCR = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t fifoNotFull = PTHREAD_COND_INITIALIZER;
static pthread_cond_t fifoNotEmpty = PTHREAD_COND_INITIALIZER;
```

So nao percebo pq o fifo é declarado como static.
Cria-se um mutex para controlar acesso a funçoes, cria-se condition variables de fifo not full e fifo not empty.

No create, é necessario verificar se o fifo(static) é NULL, tipo um assert.

```cpp
require(fifo == NULL, "fifo must not exist");
```

Depois disso, faz-se o codigo normal da função e no fim, faz-se broadcast da condiçao que o fifo não está cheio e de seguida dá se unlock ao mutex.

```cpp
cond_broadcast(&fifoNotFull);
mutex_unlock(&accessCR);
```

O destroy fica sem alterações, o isFull e isEmpty tambem.

O **in()** também assegura no inicio se o fifo existe.

```cpp
require(fifo != NULL, "fifo must exist");
```

Depois dá-se lock ao mutex.

```cpp
mutex_lock(&accessCR);
```

Agora temos que verificar num loop se o fifo está cheio, se estiver, temos que esperar que o fifo não esteja cheio.

```cpp
while (isFull())
{
    cond_wait(&fifoNotFull, &accessCR); 
}
```

Com semaforos nao era preciso nada disto.

Assim que sairmos do loop, podemos fazer o insert de um elemento e no fim fazemos broadcast da condiçao que fifo nao está vazio e de seguida dá-se unlock ao mutex.

```cpp
cond_broadcast(&fifoNotEmpty);
mutex_unlock(&accessCR);
```

Com o **out()** é igual ao in(), mas ao contrario. Faz-se na mesma o assert que o fifo deve existir, dá se lock ao mutex, faz-se o loop enquanto o fifo está vazio, sempre a verificar se a condiçao not empty foi sinalizada.

```cpp
while (isEmpty())
{
    cond_wait(&fifoNotEmpty, &accessCR); 
}
```

Faz-se o remove do elemento e no fim fazemos broadcast da condiçao que fifo nao está cheio e de seguida dá-se unlock ao mutex.

```cpp
cond_broadcast(&fifoNotFull);
mutex_unlock(&accessCR);
```

## Question 2 Designing and implementing a simple client-server application

Mesmo que ultimo guiao mas os buffers e fifos usam mutexes e condition variables e o client-server threads em vez de processos.
