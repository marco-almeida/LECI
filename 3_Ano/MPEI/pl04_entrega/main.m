% Main App
films = readcell('films.txt', 'Delimiter', '\t');
data = load('u.data');

userId = getID();
op = getChoice();
while op ~= 5

    switch op
        case 1
            watchedMovies = getMoviesAndID(userId);
        case 2
            recByUser = getRecByUser(userId);
        case 3
            recByGenre = getRecByGenre(userId);
        case 4
            movieTitles = searchTitle();
        case 5
            return
        otherwise
            disp('Invalid option')
    end

    op = getChoice();

end

