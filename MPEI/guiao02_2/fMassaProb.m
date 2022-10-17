% N = num experiencias
% p Probabilidade de sair o que queremos
% n numero de lancamentos por experiencia
% k indice
function pX = fMassaProb (N,p,k,n)
  lancamentos = rand(n,N) < p;
  sucessos = sum(lancamentos) == k;
  pX = sum(sucessos)/N;
end