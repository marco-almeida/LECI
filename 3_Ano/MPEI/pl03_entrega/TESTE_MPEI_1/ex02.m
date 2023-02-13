% obtenha a matriz L correndo o comando load('L.mat')
load('L.mat');
% a
% IDENTIFIQUE O NUMERO DE PAGINAS DE L e deadends

fprintf("Numero de paginas %d\n", length(L));
deadends = 0;

for i = 1:length(L)

    if sum(L(:, i)) == 0
        deadends = deadends + 1;
        disp(i);
    end

end

disp(deadends);

% b
H = zeros(length(L));

for i = 1:length(L)
    soma = sum(L(:, i)); % soma das páginas para quais aponta

    if (soma ~= 0)
        H(:, i) = L(:, i) / soma;
    end

end

% c
% matriz A do google com B = 0.85
A = 0.85 * H + 0.15 / length(L) * ones(length(L));

% d

% valor de pagerank de todas as paginas de forma iterativa considerando um numero minimo de iteraçoes que garanta que nenhum valor de pagerank muda mais em modulo que 10^-4 na ultima iteraçao
% e o numero de iteraçoes necessarias para atingir esse criterio
oldstate = ones(length(L), 1) / length(L);
iterations = 0;

while (1)
    newstate = A * oldstate;
    iterations = iterations + 1;

    if (max(abs(newstate - oldstate)) < 10^-4)
        break;
    end

    oldstate = newstate;
end

disp(iterations);

% e
% determine o valor de pagerank das paginas dead end que foram identificadas da alinea A
deadends = [22 25 32 51];
disp(newstate(22));
disp(newstate(25));
disp(newstate(32));
disp(newstate(51));
