chaves = load('chaves_pt.mat');
chaves = chaves.chaves;

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
    fprintf('Máx Atribuições numa posição: %d\n', max(vectorTables));
    fprintf('Tempo: %f segundos\n', toc);
    h = hashCodes/length(vectorTables);
    histogram(hashCodes, 100)
    fprintf('Momento de ordem 2: medio  - %f teorico - %f\n', mean(h.^2),1/(2+1));
    fprintf('Momento de ordem 5: medio  - %f teorico - %f\n', mean(h.^5),1/(5+1));
    fprintf('Momento de ordem 10: medio - %f teorico - %f\n\n', mean(h.^10),1/(10+1));
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
    fprintf('Máx Atribuições numa posição: %d\n', max(vectorTables));
    fprintf('Tempo: %f segundos\n', toc);
    h = hashCodes/length(vectorTables);
    histogram(hashCodes, 100)
    fprintf('Momento de ordem 2: medio  - %f teorico - %f\n', mean(h.^2),1/(2+1));
    fprintf('Momento de ordem 5: medio  - %f teorico - %f\n', mean(h.^5),1/(5+1));
    fprintf('Momento de ordem 10: medio - %f teorico - %f\n\n', mean(h.^10),1/(10+1));
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
    fprintf('Máx Atribuições numa posição: %d\n', max(vectorTables));
    fprintf('Tempo: %f segundos\n', toc);
    h = hashCodes/length(vectorTables);
    histogram(hashCodes, 100)
    fprintf('Momento de ordem 2: medio  - %f teorico - %f\n', mean(h.^2),1/(2+1));
    fprintf('Momento de ordem 5: medio  - %f teorico - %f\n', mean(h.^5),1/(5+1));
    fprintf('Momento de ordem 10: medio - %f teorico - %f\n\n', mean(h.^10),1/(10+1));
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
    fprintf('Máx Atribuições numa posição: %d\n', max(vectorTables));
    fprintf('Tempo: %f segundos\n', toc);
    h = hashCodes/length(vectorTables);
    histogram(hashCodes, 100)
    fprintf('Momento de ordem 2: medio  - %f teorico - %f\n', mean(h.^2),1/(2+1));
    fprintf('Momento de ordem 5: medio  - %f teorico - %f\n', mean(h.^5),1/(5+1));
    fprintf('Momento de ordem 10: medio - %f teorico - %f\n\n', mean(h.^10),1/(10+1));
end
