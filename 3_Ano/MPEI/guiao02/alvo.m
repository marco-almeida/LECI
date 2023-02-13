function out = alvo(n,N,m)

alvos = randi([1,m],n,N);      
sucesso = 0; %Contador de sucessos
  for c=1 : N
    if length(unique(alvos(:,c))) == n 
        sucesso = sucesso + 1;                   
    end                                                        
  end                             
  out = sucesso/N;
end