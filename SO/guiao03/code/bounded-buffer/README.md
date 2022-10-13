## Question 1 - Implementing bounded-buffer, producer-consumer application, using a shared FIFO and semaphores.

> Look at the code of the unsafe version, fifo unsafe.cpp, analyse it, and try to understand why it is unsafe

Temos producers que produzem items e os colocam num fifo. Um fifo é apenas uma estrutura de dados (first in first out - queue).
Temos consumers que consomem items que estão no fifo.

Quando o fifo está a ser acedido por uma das entidades, o **acesso a third parties teria que ser bloqueado por uma entidade**(*semaforos* a regular, por exemplo).

(Dá race condition quando o id do processo é diferente dos dois ultimos digitos do valor)
Isto é unsafe porque **um processo está a aceder a uma parte da memória que está a ser utilizada por outro processo**, ou seja o producer pode estar a modificar o fifo e a meio entra o consumer, quando não devia.

Não existe uma entidade que "barra" outros processos de acederem àquela memória enquanto está a ser usada pelo primeiro processo que lá chegou

> Look at the code of the safe version, fifo safe, and analyse it

Para aceder ao fifo, é preciso (aqui nao) que o processo verifique se semaforo está != 0. Está.
Acede ao fifo e, para que mais ninguem possa aceder, mete o semaforo a 0 (vermelho).
Faz a sua cena e no fim incrementa o semaforo (fica verde, != 0)

Mitiga o busy waiting. Acho que assume que estão a aceder ao fifo turn by turn, então assumem que ha sempre espaço pa produzir e algo pa consumir.

