%A -> Outro,e apenas mais um, filho tambem e rapaz
%B -> Pelo menos um dos filhos e rapaz
%Queremos: P(A|B) = P(AB)/P(B)
%Onde sabemos que P(AB) simboliza "Temos 2 filhos rapazes"

%P(AB)
N = 1e5; %Numero de experiencias
p = 0.5; %Probabilidade de ter um filho rapaz
k = 2;   %numero de filhos rapazes
n = 5;   %numero total de filhos (total sao 5 mas sabemos que um ja e rapaz)

filhos = rand(n,N) > p;
rapazes = sum(filhos)==k;
probSimulacao = sum(rapazes)/N;
% prob de familia ter apenas 2 filhos rapaz
%P(B)
k = 1;
rapazes = sum(filhos)>=k;
probSimulacaoB = sum(rapazes)/N;
% prob de haver pelo menos 1 filho rapaz
probSimulacaoFinal = probSimulacao/probSimulacaoB