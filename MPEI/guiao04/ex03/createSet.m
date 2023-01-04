function [users, Set] = createSet (file)
    udata = load(file); % Load File

    %u= udata(1:end,1:2); clear udata;
    %u = unique(u(:,1));
    %p = randperm(length(u),100);

    %users=zeros(100,1);
    %for i=1 : 100
    %  users(i) = u(p(i),1);
    %endfor

    % Get first 2 rows
    u = udata(1:end, 1:2); clear udata; %Get only 100 users (dont know how to do this randomly!!)

    % Users list
    users = unique(u(:, 1)); % Get users IDs
    Nu = length(users); % Total ammount of users

    % Make list of films per user
    Set = cell(Nu, 1);

    for n = 1:Nu, % For each user
        % Get users films
        ind = find(u(:, 1) == users(n));
        %And store in Set
        Set{n} = [Set{n} u(ind, 2)];
    end

end
