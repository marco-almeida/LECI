% N = num experiencias -> 1e5
% p Probabilidade de sair o que queremos -> 0.5
% n numero de lancamentos por experiencia -> 4
% k indice -> num que queremos
function pX = fMassaProb (N,p,k,n)
  lancamentos = rand(n,N) < p;
  sucessos = sum(lancamentos) == k;
  pX = sum(sucessos)/N;
end