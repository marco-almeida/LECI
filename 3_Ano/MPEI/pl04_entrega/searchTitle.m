function movieTitles = searchTitle()
    Suporte = load("Suporte.mat");
    films = Suporte.films;
    MinHashOP4 = Suporte.MinHashOP4;
    BloomFilter = Suporte.BloomFilter;
    
    str = lower(input('\nWrite a String: ', 's'));
    shingle_size = 3;
    
    % Cell array com os shingles da string introduzida
    shinglesStr = {};
    for i = 1:length(str) - shingle_size + 1
        shingle = str(i:i+shingle_size-1);
        shinglesStr{i} = shingle;
    end
    
    K = 100;
    % Fazer a MinHash dos shingles da string introduzida
    MinHashString = inf(1,K);
    for j = 1:length(shinglesStr)
        chave = char(shinglesStr{j});
        hash = zeros(1,K);
        for z = 1:K
            chave = [chave num2str(z)];
            hash(z) = DJB31MA(chave, 127);
        end
        MinHashString(1,:) = min([MinHashString(1,:); hash]);
    end
    
    % Guardar Distancias de Jaccard entre a string e cada filme
    distancesJ = ones(length(films),1);
    h = waitbar(0,'Calculating Jaccard Distances');
    for i = 1:length(films)
        waitbar(i/length(films), h);
        distancesJ(i) = sum(MinHashOP4(i,:) ~= MinHashString)/K;
    end
    delete(h);
    
    % Procurar os 5 filmes com menor Distancia
    [~, min1] = min(distancesJ);
    distancesJ(min1) = 1;
    [~, min2] = min(distancesJ);
    distancesJ(min2) = 1;
    [~, min3] = min(distancesJ);
    distancesJ(min3) = 1;
    [~, min4] = min(distancesJ);
    distancesJ(min4) = 1;
    [~, min5] = min(distancesJ);
    
    % Pesquisar o nr de vezes que cada filme teve avaliaçao >= 3
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    movieTitles = [min1 min2 min3 min4 min5];
    countVector = zeros(1,5);
    
    n = Suporte.n;
    k = Suporte.k;

    for i = 1:length(movieTitles)
        movieElem = movieTitles(i);
        minValue = inf;
    
        for j = 1:k
            movieElem = [movieElem num2str(j)];
            hash = DJB31MA(movieElem, 127);
            hash = mod(hash,n) + 1;
            count = BloomFilter(hash);
            if count < minValue
                minValue = count;
            end
        end
        countVector(i) = minValue;
    end

    % Displays
    fprintf("Answer To Search: \n\n");
    disp(films{min1});
    fprintf("Number of times with evaluation 3 or more: %d\n\n",countVector(1))

    disp(films{min2});
    fprintf("Number of times with evaluation 3 or more: %d\n\n",countVector(2))

    disp(films{min3});
    fprintf("Number of times with evaluation 3 or more: %d\n\n",countVector(3))

    disp(films{min4});
    fprintf("Number of times with evaluation 3 or more: %d\n\n",countVector(4))

    disp(films{min5});
    fprintf("Number of times with evaluation 3 or more: %d\n\n",countVector(5))

end