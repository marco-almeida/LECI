% 3. Utilizando a informacao obtida no exercıcio anterior, compare o desempenho das quatro funcoes de
% dispersao para cada tamanho diferente da Hash Table, relativamente a:

% (a) Uniformidade, de duas formas diferentes:

% i. visualize os histogramas dos hascodes com 100 intervalos e verifique se os valores nos dife-
% rentes intervalos sao similares;

% ii. calcule os momentos de ordem 2, 5 e 10 das variaveis aleat orias correspondentes aos valores
% dos hashcodes divididos pelo comprimento da Hash Table (i.e, variavel aleatoria toma valores
% entre 0 e 1) e compare com os valores teoricos da distribuicao uniforme.

% (b) Numero de colisoes e numero maximo de atribuicoes numa mesma posicao da Hash Table.
% (c) Tempos de execucao

N = 10 ^ 4;
imin = 6;
imax = 20;
caracteres = ['A':'Z' 'a':'z'];
prob_caracteres = ones(1, length(caracteres)) / length(caracteres);
chaves = gera_chaves(N, imin, imax, caracteres, prob_caracteres);

tamanhos = [5e5, 1e6, 2e6];
fprintf('\nAlgoritmo: string2hash: djb2\n');

for i = 1:length(tamanhos)
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

for i = 1:length(tamanhos)
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

for i = 1:length(tamanhos)
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

for i = 1:length(tamanhos)
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
