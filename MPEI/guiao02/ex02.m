%% a
n = 20; % dardos
m = 100; % alvos
N = 1e5; % experiencias

lancamentos = randi([1,m], n, N);
for a = 1:N
    unique(lancamentos(:,a))
end

