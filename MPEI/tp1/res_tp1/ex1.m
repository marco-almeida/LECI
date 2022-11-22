%% b
%    D   O   F    U
T = [0.4 0.2 0.25 0.25
     0.2 0.4 0.25 0.25
     0.2 0.2 0.25 0.25
     0.2 0.2 0.25 0.25];

%% c
x = [0 1 0 0]';
res = T^7*x;
disp(res(3));

%% d
M = [T - eye(length(T));ones(1,length(T))];
x = [zeros(length(T),1); 1];
u = M\x;
disp(u(2));

%% e
% DDxxxFF
% Primeira sessão o cenário é escolhido aleatoriamente -> prob 0,25 D
prob2D = 0.25*0.4;
x = [prob2D 0 0 0]';
probDDeF = T^4*x;
probDDeFF = probDDeF(3)*0.25;
disp(probDDeFF);

