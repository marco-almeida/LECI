% GetMovies - lista os filmes que o utilizador viu
% cada linha mostra o ID e Título de um filme

function movies = getMoviesAndID(ID)
    Suporte = load("Suporte.mat");
    udata = Suporte.udata;
    films = Suporte.films;
    
    movies = {};

    for i = 1:size(udata, 1)
        if udata(i, 1) == ID
            movies = [movies; [udata(i, 2), films(udata(i, 2), 1)]];
        end
    end
    
    % Display List of Movies
    fprintf("List of Movies You Watched:\n")
    for i = 1:length(movies)
        disp(movies(i,:))
    end

end