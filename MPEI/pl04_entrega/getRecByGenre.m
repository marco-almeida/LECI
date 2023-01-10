% Get Recommendation based on Genre
% Para cada filme avaliado pelo utilizador, procura filmes cuja distJaccard
% seja menor que 0.8 e ainda não foram vistos pelo utilizador e guarda-os
% num conjunto. Devolve os 2 filmes que aparecem em mais conjuntos.

function recommendation = getRecByGenre(ID)
    Suporte = load("Suporte.mat");
    moviesByUser = Suporte.moviesByUser;
    films = Suporte.films;
    MinHashOP3 = Suporte.MinHashOP3;
    
    % Criar Lista dos Filmes que o Utilizador já avaliou
    moviesOfCurrentUser = moviesByUser{ID};
    numberOfMovies = length(moviesOfCurrentUser);
    
    SetOfSimilars = cell(numberOfMovies,1); % Estrutura para guardar conjuntos
    counter = zeros(1,length(MinHashOP3));  % contador dos conjuntos
    
    h = waitbar(0, 'Calculating Jaccard Distances');
    K = 150;
    for i = 1:numberOfMovies
        waitbar(i/numberOfMovies, h);
        movieID = moviesOfCurrentUser(i);

        for j = 1:length(MinHashOP3)
            distJ = sum(MinHashOP3(j,:) ~= MinHashOP3(movieID,:))/K;
            if distJ < 0.8
                if isempty(intersect(moviesOfCurrentUser,j)) % verificar que não pertence aos 
                                                             % filmes vistos pelo utilizador
                    SetOfSimilars{i} = [SetOfSimilars{i} j];
                    counter(j) = counter(j) + 1;
                end
            end
        end 
    end
    delete(h);
    
    % Procurar os 2 filmes com maior contagem
    [~, max1] = max(counter);
    counter(max1) = 0;
    [~, max2] = max(counter);
    
    fprintf("List of Films By Similar Genre:\n\n");
    disp(films{max1});
    disp(films{max2});

    recommendation = [max1 max2];

end