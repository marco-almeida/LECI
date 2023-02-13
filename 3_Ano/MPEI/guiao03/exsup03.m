T = [0 1/2 1/2 0 1/4 0
    1 0 0 0 1/4 0
    0 0 0 1 1/4 0
    0 0 1/2 0 1/4 1
    0 0 0 0 0 0
    0 1/2 0 0 0 0];

A = 0.85 * T + 0.15 * ones(6) / 6;

% pagerank inicial
x = ones(6, 1) / 6;

while 1
    x_old = x;
    x = A * x;

    if max(abs(x - x_old)) < 0.01
        break
    end

end

disp(max(x, [], 'all'))
disp(min(x, [], 'all'))
