function chaves = key_gen(N, imin, imax, caracteres, prob)

    if nargin == 4
        prob = 1 / length(caracteres);
    end

    chaves = cell(1, N);

    for i = 1:N
        comprimento = randi([imin imax]);
        chaves{i} = one_key_gen(comprimento, caracteres, prob);
    end

end

% fazer funçao gera chave que recebe o comprimento, caracteres e probabilidade, tem que usar o prob
% para gerar a chave

function chave = one_key_gen(comprimento, caracteres, prob)
    chave = '';

    for i = 1:comprimento
        probable_char = discrete_rnd(caracteres, prob); % obter random character de acordo com as probabilidades
        chave = strcat(chave, probable_char); % concatenar esse random character com o que ja existe
    end

end

% Generate randomly the next state.
% Inputs:
% states = vector with state values
% probVector = probability vector
% e.g states = [1 2 3 4]
% probVector = [0.1 0.2 0.3 0.4]
function state = discrete_rnd(states, probVector)
    U = rand();
    i = 1 + sum(U > cumsum(probVector));
    state = states(i);
end
