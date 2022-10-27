# Guiao03 - Processes, shared memory, and semaphores - #IPC/02

## Try to understand how shared memory is created and used.

		Cria-se memoria partilhada com shmget(sizeof Fifo) e dá se mapping com shmat

## Try to understand how shared memory is created and used.

		O fifo_unsafe é unsafe porque cria um fifo em memoria partilhada, mas nao faz qualquer serviço de mutex para controlar acessos de processos. Por outras palavras, deixa toda a gente aceder ao mesmo tempo acho eu

## What should be done to solve the problem?

		implementar semaforos



