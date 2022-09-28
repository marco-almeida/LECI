a) Antes de qualquer filhos, é utilizado o printf 2 vezes; Depois do fork(), cada printf é produzido 2 vezes.
O pai dá print primeiro

b) o ret = 0 é retornado ao filho, o ret positivo é retornado ao caller = pai

c) o execl descarta o processo corrente e mete outro por cima a correr, entao o printf à frente nao vai funcionar;
vai apenas dar um salto para o programa novo
em execl(file,arg0...,NULL)
> file é filename
arg0 tem que ser obrigatoriamente o filename 
> The first argument, arg0, is required and must contain the name of the executable file for the new process image
e o ultimo argumento é sempre NULL

nsei
> A process ID value of 1 indicates that there is no parent process associated with the calling process
nsei

d)
o wait mete o processo do pai à espera que os processos dos filhos acabem e depois continua. entao nao faz nada visivel

no 2b), chamar kill -TERM (term porque queremos mandar um sigTERM ao processo) <PID>

kill -TERM ou kill -15 é igual porque 15 é o codigo pa term 
