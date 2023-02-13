function is = isMember(vector, element, k)
    % if index value returned by hash function is 0, element is not in vector
    if k >= 1
        index = getHashcode1(vector, element);
        if vector(index) == 0
            is = false;
            return;
        end
    end
    if k >= 2
        index = getHashcode2(vector, element);
        if vector(index) == 0
            is = false;
            return;
        end
    end
    if k >= 3
        index = getHashcode3(vector, element);
        if vector(index) == 0
            is = false;
            return;
        end
    end
    is = true;
end

% auxiliary function to get hashcode vector
function hashcode = getHashcode1(vector, element)
    hashcode = mod(string2hash(element, 'djb2'), length(vector)) + 1;
end

function hashcode = getHashcode2(vector, element)
    hashcode = mod(string2hash(element, 'sdbm'), length(vector)) + 1;
end

function hashcode = getHashcode3(vector, element)
    hashcode = mod(hashstring(element, length(vector)), length(vector)) + 1;
end