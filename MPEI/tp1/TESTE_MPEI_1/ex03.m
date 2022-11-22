% b
%    1   3     5   7     9    FIM
T = [0 0.8/3 0 0.8/3 0 0
    1/3 0 0.5 0 0.4 0
    1/3 0.8/3 0 0.8/3 0 0
    1/3 0 0.5 0 0.4 0
    0 0.8/3 0 0.8/3 0 0
    0 0.2 0 0.2 0.2 0
    ];

% c
A = [1 2 3 4 5 6];
letras = ['1' '3' '5' '7' '9' '.'];
probs = [0.25 0.125 0.25 0.25 0.125];
randomIndex = discrete_rnd([1 2 3 4 5], probs);

gen = crawl(T, randomIndex, 6);
palavra = zeros(1, length(gen) - 1);

for i = 1:length(gen) - 1
    palavra(i) = letras(gen(i));
end

char(palavra)

% d
% simule a geraçao de 10^6 strings aleatorias (com cell arays) para obter uma lista de strings geradas

N = 10^6;
c_array = {N};

for i = 1:N
    randomIndex = discrete_rnd([1 2 3 4 5], probs);
    gen = crawl(T, randomIndex, 6);
    palavra = zeros(1, length(gen) - 1);

    for k = 1:length(gen) - 1
        palavra(k) = letras(gen(k));
    end

    c_array{i} = char(palavra);
    clear palavra
    clear gen
end

% e
% com base na simulaçao anterior, estime a probabilidade da string '13579'
counter_1 = 0;

for i = 1:N

    if strcmp(c_array{i}, '13579')
        counter_1 = counter_1 + 1;
    end

end

disp(counter_1 / N)

% f
% calcule algebricamente a probabilidade da string '13579'
s1 = 0.25;
s2 = 1/3;
s3 = 0.8/3;
s4 = 0.5;
s5 = 0.8/3;
s6 = 0.2;
disp(s1 * s2 * s3 * s4 * s5 * s6);

% g
% calcule o comprimento medio das strings produzidas pelo gerador aleatorio e que começam pelo digito '1'
counter_2 = 0;
comprimentos = 0;

for i = 1:N

    if c_array{i}(1) == '1'
        counter_2 = counter_2 + 1;
        comprimentos = comprimentos + length(c_array{i});
    end

end

disp(comprimentos / counter_2);

% anexo
function [state] = crawl(H, first, last)
    % the sequence of states will be saved in the vector "state"
    % initially, the vector contains only the initial state:
    state = [first];
    % keep moving from state to state until state "last" is reached:
    while (1)
        state(end + 1) = nextState(H, state(end));

        if ismember(state(end), last) % verifies if state(end) is absorbing
            break;
        end

    end

end

% Returning the next state
% Inputs:
% H - state transition matrix
% currentState - current state
function state = nextState(H, currentState)
    % find the probabilities of reaching all states starting at the current one:
    probVector = H(:, currentState)'; % probVector is a row vector
    n = length(probVector); %n is the number of states
    % generate the next state randomly according to probabilities probVector:
    state = discrete_rnd(1:n, probVector);
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
