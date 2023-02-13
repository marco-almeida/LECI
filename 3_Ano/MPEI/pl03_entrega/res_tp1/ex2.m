%% a

load("L.mat");

disp(sum(L));
%por coluna: 1 se tem ligação para a página e 0 se não tem

% N -> 110 páginas
% a partir da soma vêmos que as páginas 19,64,66 e 85 são dead-ends

%% b

H = zeros(length(L));
for i = 1:length(L)
    soma = sum(L(:,i));  % soma das páginas para quais aponta
    if (soma ~= 0)
        H(:,i) = L(:,i)/soma;
    end
end

disp(H);
%disp(sum(H));

%disp(length(L));

%% c
% for i = 1:length(L)
%     soma = sum(L(:,i));  % soma das páginas para quais aponta
%     if (soma == 0)
%         H(:,i) = 1/110;
%     end
% end

b = 0.85;
A = b * H +(1 - b)* ((1/length(H)) * ones(length(H)));
A(isnan(A)) = 1/length(H)

%disp(A);
%disp(sum(A));

%% d
x0=ones(length(H),1)/length(H);
% -----------------------
iter=1;
x=x0;
epsilon=1e-3;
while 1
    %fprintf(1,'iteração %d\n',iter);
    xold=x;
    x=A*x;
    if max(abs(x-xold))<epsilon break ; end
    iter=iter+1;
end

%% e
[PR idx]=sort(x,'descend');
disp(PR);
for i = 1:length(PR)
    fprintf('PageRank=%f : %d \n',PR(i),idx(i));
end







