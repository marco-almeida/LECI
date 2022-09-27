%P(AB): ter 2 filhos
N = 1e5; %Numero de experiencias
p = 0.5; %Probabilidade de ter um filho rapaz
k = 2;   %numero de filhos rapazes
n = 2;   %numero total de filhos

filhos = rand(n,N) > p;
rapazes = sum(filhos) == k;
probSimulacao = sum(rapazes)/N

%P(B): ter pelo menos 1 filho
k = 1;
rapazes = sum(filhos)>=k; %Pois queremos a prob. de ter PELO MENOS um filho (ou seja k == 1 ou k == 2)
probSimulacaoB = sum(rapazes)/N

probSimFinal = probSimulacao/probSimulacaoB