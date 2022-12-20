clear all;

udata = load('u.data');
u = udata(1:end, 1:2);
clear udata;
users = unique(u(:, 1));
Nu = length(users);
Set = cell(Nu, 1);

for n = 1:Nu
    ind = find(u(:, 1) == users(n));
    Set{n} = [Set{n} u(ind, 2)];
end

tic
K = 200;
MinHashValue = inf(Nu, K);

for i = 1:Nu
    conjunto = Set{i};

    for j = 1:length(conjunto)
        chave = char(conjunto(j));
        hash = zeros(1, K);

        for kk = 1:K
            chave = [chave num2str(kk)];
            hash(kk) = DJB31MA(chave, 127);
        end

        MinHashValue(i, :) = min([MinHashValue(i, :); hash]);
    end

end

fprintf('Tempo de calculo das MinHash= %f\n', toc);
tic
J = zeros(Nu);
h = waitbar(0, 'Calculating');

for n1 = 1:Nu
    waitbar(n1 / Nu, h);

    for n2 = n1 + 1:Nu
        J(n1, n2) = sum(MinHashValue(n1, :) ~= MinHashValue(n2, :)) / K;
    end

end

fprintf('Tempo de calculo das distancias dadas por MinHash= %f\n', toc);
delete (h)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% tic
% J=zeros(Nu);
% h=waitbar(0, 'Calculating');
% for n1 = 1:Nu
%     waitbar(n1/Nu, h);
%     fo1 - length(I)/length(U);r n2 = n1+1:Nu
%         I = intersect(Set{n1}, Set{n2});
%         U = union(Set{n1}, Set{n2});
%         J(n1, n2) =
%     end
% end
% delete (h)
% fprintf('Tempo de calculo das distancias= %f\n', toc);
% save 'J.mat'

tic
threshold = 0.4;
SimilarUsers = zeros(1, 3);
k = 1;

for n1 = 1:Nu

    for n2 = n1 + 1:Nu

        if J(n1, n2) <= threshold
            SimilarUsers(k, :) = [users(n1) users(n2) J(n1, n2)];
            k = k + 1;
        end

    end

end

fprintf('Tempo de caluclo dos utilizadores mais similares= %f\n', toc);
fprintf('No de pares mais similares= %d\n', size(SimilarUsers, 1));
SimilarUsers
