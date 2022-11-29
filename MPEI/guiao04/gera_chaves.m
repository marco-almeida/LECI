% 1
% Crie uma funcao para gerar chaves constituıdas por caracteres. O comprimento (i.e., o numero de ca-
% racteres) de cada chave deve ser escolhido aleatoriamente (distribuicao uniforme) entre imin e imax. A
% funcao deve ter como parametros de entrada o ńumero N de chaves a gerar, os valores de imin e imax, um
% vector com os caracteres a usar nas chaves e um vector com as probabilidades de cada um dos caracteres
% a utilizar. Se a funcao for chamada sem o ultimo parametro, deve considerar os caracteres equiprovaveis
% (ver a documentacao da funcao nargin).

function chaves = gerachaves(N, imin, imax, caracteres, prob)

    if nargin == 4
        prob = 1 / length(caracteres);
    end

    chaves = cell(1, N);

    for i = 1:N
        comprimento = randi([imin imax]);
        chaves{i} = gerachave(comprimento, caracteres, prob);
    end

end

% fazer funçao gera chave que recebe o comprimento, caracteres e probabilidade, tem que usar o prob
% para gerar a chave

function chave = gerachave(comprimento, caracteres, prob)
    chave = '';

    for i = 1:comprimento
        probable_char = discrete_rnd(caracteres, prob);
        chave = strcat(chave, probable_char);
    end

end

% Generate randomly the next state.
% Inputs:
% states = vector with state values
% probVector = probability vector
function state = discrete_rnd(states, probVector)
    U = rand();
    i = 1 + sum(U > cumsum(probVector));
    state = states(i);
end
