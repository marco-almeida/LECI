N = 1e5; %numero de experiencias
p = 0.3; %probabilidade de defeito
n = 5; % numero de peças na amostra
k = 3; % numero de peças serem defeituosas

r1 = rand(n, N); % n linhas e N colunas para repetiçao
r1Prob = r1 <= p; % se forem maior que 0.3 dá 0, else dá 1(defeituosa)
smpl = sum(r1Prob); % somar as colunas entre si e ficar com vetor
chances = smpl == k; % ver se as somas de cada elemento são == k
res = sum(chances) / N
