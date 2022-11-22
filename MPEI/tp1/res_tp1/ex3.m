%% b
%    A    C    O    R    T    Fim
T = [0    0.5  0    4/15 0.5  0
     4/15 0    4/15 0    0    0
     0    0.5  0    4/15 0.5  0
     4/15 0    4/15 0    0    0
     4/15 0    4/15 4/15 0    0
     0.2  0    0.2  0.2  0    0];

%% c
x = randi(5,1,1); % Random initial state
state = crawl(T,x,6);
disp(state);

%% d
N = 10^4;
c = cell(1,N);
for i = 1:N
    x = randi(5,1,1);
    c(i) = {crawl(T,x,6)};
end

%% e) e g)
cont = 0;
tlength = 0;
numC = 0;
for i = 1:N
    a = c{i};
    if isequal([2 1 5 3 6],a)
        cont = cont + 1;
    end
    if a(1) == 2 %Começa em C
        tlength = tlength + length(a);
        numC = numC + 1;
    end
end
prob = cont/N;
mediaC = tlength/numC;
disp(prob);
disp(mediaC);

%% f
resE = 1/6 * 1/2 * 4/15 * 1/2 * 3/15;
disp(resE);

%% crawl
function [state] = crawl(H, first, last)
     % the sequence of states will be saved in the vector "state"
     % initially, the vector contains only the initial state:
     state = [first];
     % keep moving from state to state until state "last" is reached:
     while (1)
          state(end+1) = nextState(H, state(end));
          if ismember(state(end), last) % verifies if state(end) is absorving
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
     probVector = H(:,currentState)';  % probVector is a row vector 
     n = length(probVector);  %n is the number of states
     % generate the next state randomly according to probabilities probVector:
     state = discrete_rnd(1:n, probVector);
end

% Generate randomly the next state.
% Inputs:
% states = vector with state values
% probVector = probability vector 
function state = discrete_rnd(states, probVector)
     U=rand();
     i = 1 + sum(U > cumsum(probVector));
     state= states(i);
end