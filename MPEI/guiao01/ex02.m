%% Codigo 1 - segunda versao

N= 1e6; %numero de experiencias
p = 0.5; %probabilidade de cara
k = 6; %nu´mero de caras
n = 15; %nu´mero de lancamentos
lancamentos = rand(n,N) > p;
sucessos= sum(lancamentos)==k;
probSimulacao= sum(sucessos)/N