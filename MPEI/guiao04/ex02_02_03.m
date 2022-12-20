palavras = fopen("PL04_ficheiros_complementares\wordlist-preao-20201103.txt", 'r');

n = 8000;
m = 1000;
m2 = 10000;
k = 3;

dicionario = textscan(palavras, '%s');
fclose(palavras);
dicionario = dicionario{1, 1}; %separa as plavras do dic para não ser 994898×1
% mas sim 1x1, cada linha com uma palavra unica
BF = inicializar(n); %tamanho n = 8000

for i = 1:m %adicionar 1000 palavras
    BF = adicionarElemento(dicionario{i}, BF, k); % com k = 3, fcs de dispersão
end

%% exc2
contador = 0;

for i = 1:m
    contem = membro(dicionario{i}, BF, k);

    if ~contem
        contador = contador + 1;
    end

end

fprintf('No. false negativos = %d\n', contador)

%% exc3
contador = 0;

for i = m + 1:m + m2
    contem = membro(dicionario{i}, BF, k);

    if contem
        contador = contador + 1;
    end

end

fprintf('Perc. falsos positivos = %.2f%%\n', contador / 100);

%% funções
function BF = inicializar(n)
    BF = zeros(1, n);
end

function BF = adicionarElemento(elemento, BF, k)
    n = length(BF);

    for i = 1:k
        elemento = [elemento num2str(i)];
        h = DJB31MA(elemento, 127);
        h = mod(h, n) + 1;
        BF(h) = 1;
    end

end

function contem = membro(elemento, BF, k)
    n = length(BF);
    contem = true;

    for i = 1:k
        elemento = [elemento num2str(i)];
        h = DJB31MA(elemento, 127);
        h = mod(h, n) + 1;

        if ~BF(h)
            contem = false;
            break; %until ( (i==k) | (B [h] == 0) )
        end

    end

end

function h = DJB31MA(chave, seed)
    % implementação da hash function DJB31MA com base no algoritmo obtido
    % no resumo 2014(PJF) que está em C
    %
    %  chave    array de caracteres com a chave
    %  seed     semente que permite obter vários hash codes para a mesma chave
    %
    %  h        hashcode devolvido
    len = length(chave);
    chave = double(chave);
    h = seed;

    for i = 1:len
        h = mod(31 * h + chave(i), 2 ^ 32 -1);
    end

end
