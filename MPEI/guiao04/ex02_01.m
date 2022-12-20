% Os objetivos destes exercıcios sao criar e testar um modulo que suporte a criacao de Bloom filters (ex: [2,
% Cap. 4: Mining Data Streams]).
% Crie um conjunto de funcoes Matlab que implementem as funcionalidades de um Bloom Filter basico. As
% funcoes devem ter os parametros necessarios para que seja possıvel criar Bloom Filters de diferentes tamanhos
% (n) e a utilizacao de diferentes numeros de funcoes de dispersao (k). Na criacao das diferentes funcoes de
% dispersao, use o metodo descrito no slide 49 da apresentacao TP sobre funcoes de dispersao com a funcao que
% melhor desempenho teve na seccao 4.1 anterior.

% Sugestao: Criar pelo menos 3 funcoes [1, sec. 3.2]: uma para inicializar a estrutura de dados; outra para
% inserir um elemento (ou elementos) no filtro; uma terceira para verificar se um elemento pertence ao conjunto.

% 1. Com as funcoes que desenvolveu, crie um Bloom Filter para guardar um conjunto, U1, de 1000 palavras
% diferentes. Use um Bloom Filter de tamanho n = 8000 e k = 3 funcoes de dispersao
clc;
clear all;

n = 8000;
m = 1000;
m2 = 10000;
k = 3;

fid = fopen('wordlist-preao-20201103.txt','r');
dicionario = textscan(fid,'%s');
fclose(fid);
dicionario = dicionario{1,1};
BF = inicializar(n);

for i = 1:m
    BF = inserir(dicionario{i}, BF, k);
end
contador = 0;
for i = 1:m
    check = verificar(dicionario{i}, BF, k);
    if ~check
        contador = contador + 1;
    end
end
fprintf('No. false negativos = %d\n', contador);


contador = 0;
for i = m+1:m+m2
    check = verificar(dicionario{i}, BF, k);
    if check
        contador = contador + 1;
    end
end
fprintf('Pec. falsos positivos = %.2f%%\n', 100*contador/m2);
fprintf('Pec. falsos positivos teórica = %.2f%%\n', 100*(1-exp(-k*m/n))^k);

function BF = inicializar(n)
    BF = false(1,n);
end

function BF = inserir(elemento, BF, k)
    n = length(BF);
    for i = 1:k
        elemento = [elemento num2str(i)];
        h = DJB31MA(elemento, 127);
        h = mod(h,n) + 1; %para dar valor entre 1 e n para por no BF
        BF(h) = true;
    end
end

function check = verificar(elemento, BF, k)
    n = length(BF);
    check = true;
    for i = 1:k
        elemento = [elemento num2str(i)];
        h = DJB31MA(elemento, 127);
        h = mod(h,n) + 1; %para dar valor entre 1 e n para por no BF
        if ~BF(h)
            check = false;
            break;
        end
    end
end