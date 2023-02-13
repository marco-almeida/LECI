%% Codigo 1 - segunda versao

N= 1e6; %numero de experiencias
p = 0.5; %probabilidade de cara
k = 2; %nu´mero de caras
n = 3; %nu´mero de lancamentos
lancamentos = rand(n,N) > p;
sucessos= sum(lancamentos)==k;
probSimulacao= sum(sucessos)/N