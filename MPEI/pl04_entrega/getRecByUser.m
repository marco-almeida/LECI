% Get Recommendation based on other Users
% Procura os 2 utilizadores mais similares e devolve os filmes desses
% utilizadores (excluindo os que já foram vistos pelo User da app)

function moviesFromBoth = getRecByUser(ID)
    Suporte = load("Suporte.mat");
    moviesByUser = Suporte.moviesByUser;
    numberOfUsers = Suporte.numberOfUsers;
    MinHashOP2 = Suporte.MinHashOP2;
    films = Suporte.films;

    distancesJ = ones(1, numberOfUsers);

    h = waitbar(0, 'Calculating Jaccard Distances');
    K = 100;
    for i = 1:numberOfUsers
        waitbar(i/numberOfUsers, h);
        if i ~= ID
            % Calculamos a distancia de Jaccard para todos os pares possiveis desse user
            distancesJ(i) = sum(MinHashOP2(i,:) ~= MinHashOP2(ID,:))/K;  
        end
    end
    delete(h);
    
    % Procurar os 2 utilizadores com menor distancia de Jaccard
    [~, min1] = min(distancesJ);
    distancesJ(min1) = 1;
    [~, min2] = min(distancesJ);

    fprintf("Similar user %d\n",min1) % Imprime no Terminal 
    fprintf("Similar user %d\n\n",min2) % o ID dos 2 utilizadores     
    
    % Inserir num vetor os filmes dos 2 utilizadores encontrados
    
    A = cell2mat(moviesByUser(ID));         % A = Lista de filmes do utilizador
    B = cell2mat(moviesByUser(min1));       % B = Lista de filmes do minFirst
    C = intersect(A,B);                     % C = Filmes de Ambos
    moviesFromFirst = setdiff(B,C);         % moviesFromFirst = filmes de minFirst que utilizador desconhece
    
    B = cell2mat(moviesByUser(min2));       % B = Lista de filmes do minFirst
    C = intersect(A,B);                     % C = Filmes de Ambos
    moviesFromSecond = setdiff(B,C);        % moviesFromSecond = filmes de minSecond que utilizador desconhece
    
    moviesFromBoth = union(moviesFromFirst,moviesFromSecond);
    
    % Print List of Movies
    fprintf("List of Movies By Similar User:\n\n")
    for i = 1:length(moviesFromBoth)
        disp(films{moviesFromBoth(i)})
    end

end