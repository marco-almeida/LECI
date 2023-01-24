% representar matriz T
%   1 2 3 4 5 META
% 1
% 2
% 3
% 4
% 5
% META
T = [0     0 0     0     0      0
     0.2   0 0     0     0      0
     0     0 0     0.1   0.45   0
     0     0 0.3   0     1-0.45 0
     1-0.2 1 1-0.3 0     0      0
     0     0 0     1-0.1 0      1]

% menor percurso para jogador ir de estado 1 para 6 (meta) é 1,5,4,Meta
p1_5 = T(5,1);
p5_4 = T(4,5);
p4_meta = T(6,4);
p = p1_5 * p5_4 * p4_meta

% determine o numero medio de casa percorridas desde o inicio ate ao fim do jogo (incluindo a casa inicial e final).

Q = T(1:5,1:5);
F = (eye(5) - Q)^-1;
x1 = [1 0 0 0 0]';
media = sum(F*x1)