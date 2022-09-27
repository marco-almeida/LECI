N= 1e6; %numero de familias
p = 0.5; %probabilidade de rapaz
k = 1; %nu´mero de filhos
n = 2; %filhos totais
familias = rand(n,N) > p; % cada coluna é uma familia com 2 filhos
rapazes = sum(familias) >= k; % pelo menos cada familia com 1 filho 
probSimulacao = sum(rapazes)/N 