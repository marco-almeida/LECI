% 2. Considere a funcao Matlab string2hash() que implementa duas funcoes de dispersao diferentes.
% Considere ainda 2 funcoes Matlab fornecidas que sao adaptacoes para Matlab das funcoes de dispersao
% hashstring() e DJB31MA().

% Utilizando separadamente cada uma destas quatro funcoes de dispersao, simule a insercao das chaves
% criadas no exercıcio 1a) em 3 Chaining Hash Tables, uma de tamanho 5 x 10e5, outra de tamanho 10e6 e
% a terceira de tamanho 2 x 10e6. Para cada uma das simulacoes (4 funcoes de dispersao x 3 sizes):

%1 - a)
N = 10^4;
imin = 6;
imax = 20;
caracteres = ['A':'Z' 'a':'z'];
prob_caracteres = ones(1, length(caracteres)) / length(caracteres);
chaves = gera_chaves(N, imin, imax, caracteres, prob_caracteres);

% a) Guarde um vetor com os hashcodes obtidos.
% b) Registe o numero de atribuicoes a cada uma das posicoes de cada Hash Table.
% c) Calcule o numero de colisoes (em cada Hash Table e para cada funcao de dispersao).
% d) O tempo de execucao da simulacao.
tamanhos = [5e5, 1e6, 2e6];
fprintf('\nAlgoritmo: string2hash: djb2\n');

for i = 1:length(sizes)
    collisions = 0;
    vectorTables = zeros(1, tamanhos(i));
    hashCodes = zeros(1, length(chaves));
    tic

    for j = 1:length(chaves)
        hashCodes(j) = mod(string2hash(chaves{j}, 'djb2'), length(vectorTables)) + 1;

        if vectorTables(hashCodes(j)) > 0
            collisions = collisions + 1;
        end

        vectorTables(hashCodes(j)) = vectorTables(hashCodes(j)) + 1;
    end

    fprintf('\nHashTable de tamanho %d\n', tamanhos(i));
    fprintf('Colisões: %d\n', collisions);
    fprintf('Atribuições: %d\n', max(vectorTables));
    fprintf('Tempo: %f segundos\n', toc);
end

fprintf('\nAlgoritmo: string2hash: sdbm\n');

for i = 1:length(sizes)
    collisions = 0;
    vectorTables = zeros(1, tamanhos(i));
    hashCodes = zeros(1, length(chaves));
    tic

    for j = 1:length(chaves)
        hashCodes(j) = mod(string2hash(chaves{j}, 'sdbm'), length(vectorTables)) + 1;

        if vectorTables(hashCodes(j)) > 0
            collisions = collisions + 1;
        end

        vectorTables(hashCodes(j)) = vectorTables(hashCodes(j)) + 1;
    end

    fprintf('\nHashTable de tamanho %d\n', tamanhos(i));
    fprintf('Colisões: %d\n', collisions);
    fprintf('Atribuições: %d\n', max(vectorTables));
    fprintf('Tempo: %f segundos\n', toc);
end

fprintf('\nAlgoritmo: hashstring\n');

for i = 1:length(sizes)
    collisions = 0;
    vectorTables = zeros(1, tamanhos(i));
    hashCodes = zeros(1, length(chaves));
    tic

    for j = 1:length(chaves)
        hashCodes(j) = mod(hashstring(chaves{j}, tamanhos(i)), length(vectorTables)) + 1;

        if vectorTables(hashCodes(j)) > 0
            collisions = collisions + 1;
        end

        vectorTables(hashCodes(j)) = vectorTables(hashCodes(j)) + 1;
    end

    fprintf('\nHashTable de tamanho %d\n', tamanhos(i));
    fprintf('Colisões: %d\n', collisions);
    fprintf('Atribuições: %d\n', max(vectorTables));
    fprintf('Tempo: %f segundos\n', toc);
end

fprintf('\nAlgoritmo: DJB31MA\n');

for i = 1:length(sizes)
    collisions = 0;
    vectorTables = zeros(1, tamanhos(i));
    hashCodes = zeros(1, length(chaves));
    tic

    for j = 1:length(chaves)
        hashCodes(j) = mod(DJB31MA(chaves{j}, tamanhos(i)), length(vectorTables)) + 1;

        if vectorTables(hashCodes(j)) > 0
            collisions = collisions + 1;
        end

        vectorTables(hashCodes(j)) = vectorTables(hashCodes(j)) + 1;
    end

    fprintf('\nHashTable de tamanho %d\n', tamanhos(i));
    fprintf('Colisões: %d\n', collisions);
    fprintf('Atribuições: %d\n', max(vectorTables));
    fprintf('Tempo: %f segundos\n', toc);
end
