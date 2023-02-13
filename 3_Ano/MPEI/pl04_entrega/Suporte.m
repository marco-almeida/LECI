% Ficheiro de Suporte à app
% Serve para criar as estruturas de dados necessárias
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
udatax = load('u.data');                            % Dados de users
films = readcell('films.txt', 'Delimiter', '\t');   % Lista de Filmes

% Udata fica apenas com as duas primeiras colunas
udata = udatax(:,1:2);

% Lista de utilizadores
users = unique(udata(:,1));    % Extrai os IDs dos utilizadores
numberOfUsers = length(users); % Numero de utilizadores

% Constroi estrutura para armazenar a lista de filmes por utilizador
moviesByUser = cell(numberOfUsers,1);
% Para cada utilizador Obtem os filmes de cada um
% e insere-os na estrutura
for i = 1:numberOfUsers                     
    lines = find(udata(:,1) == users(i));   
    moviesByUser{i} = [moviesByUser{i} udata(lines,2)];
end

K = 100; % Numero de funçoes de dispersao
MinHashOP2 = inf(numberOfUsers,K);

% Para cada utilizador, faz-se MinHash ao conjunto de filmes vistos por ele
h= waitbar(0,'Calculating MinHash Op2');
for i = 1:numberOfUsers
    waitbar(i/numberOfUsers,h);
    setOfMovies = moviesByUser{i};

    for j = 1:length(setOfMovies)
        chave = char(setOfMovies(j));
        hash = zeros(1,K);
        for z = 1:K
            chave = [chave num2str(z)];
            hash(z) = DJB31MA(chave,127);
        end
        % Valor minimo da hash para este filme%
        MinHashOP2(i,:) = min([MinHashOP2(i,:); hash]);
    end
end
delete (h)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Constroi estrutura para armazenar a lista de géneros por filme
genresByFilm = cell(length(films),1);
% Para cada filme Obtem os géneros de cada um
% e insere-os na estrutura
for i = 1:length(films) 
    genresByFilm{i} = films(i,2:7);
end

K = 100; % Numero de funçoes de dispersao
MinHashOP3 = inf(length(films),K);

h= waitbar(0,'Calculating MinHash Op3');
for i = 1:length(films)
    waitbar(i/length(films),h);
    setOfGenres = genresByFilm{i};

    for j = 1:length(setOfGenres)
        % Cell pode estar vazia devido ao numero de géneros não ser
        % constante por filme
        % Se cell for vazia não é considerada para o MinHash
        if ismissing(setOfGenres{j}) == 0 
            chave = char(setOfGenres{j});
            hash = zeros(1,K);
            for z = 1:K
                chave = [chave num2str(z)];
                hash(z) = DJB31MA(chave,127);
            end
            % Valor minimo da hash para este filme
            MinHashOP3(i,:) = min([MinHashOP3(i,:);hash]);
        end

    end
end
delete (h)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

shingle_size = 3; % Tamanho do shingle
K = 100;          % Numero de funcoes de dispersao
MinHashOP4 = inf(length(films),K);

h = waitbar(0,'Calculating MinHash Op4');
for i = 1:length(films)
    waitbar(i/length(films),h);
    
    titulo = lower(films{i,1});
    shingles = {};
    % Criacao de shingles para cada filme
    for j = 1:length(titulo) - shingle_size + 1 
        shingle = titulo(j:j+shingle_size-1);
        shingles{j} = shingle;
    end

    for j = 1:length(shingles)
        chave = char(shingles(j));
        hash = zeros(1,K);
        for z = 1:K
            chave = [chave num2str(z)];
            hash(z) = DJB31MA(chave,127);
        end
        % Valor minimo da hash para este shingle
        MinHashOP4(i,:) = min([MinHashOP4(i,:);hash]);
    end
end
delete(h);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

m = length(films);
n = 8 * length(films);
k = 3;

BloomFilter = zeros(1,n);
h= waitbar(0,'Calculating Bloom Filter');
for i = 1:length(udatax)
    waitbar(i/length(udatax),h);
    movieElem = udatax(i,2); 

    if udatax(i,3) >= 3
        for j = 1:k
            movieElem = [movieElem num2str(j)];
            hash = DJB31MA(movieElem, 127);
            hash = mod(hash,n) + 1;
            BloomFilter(hash) = BloomFilter(hash) + 1;
        end
    end

end
delete(h);

% Gravar as variáveis necessária em Suporte.mat
save ('Suporte','films','udata','moviesByUser','numberOfUsers','MinHashOP2','MinHashOP3',"MinHashOP4","BloomFilter","n","k"); 
