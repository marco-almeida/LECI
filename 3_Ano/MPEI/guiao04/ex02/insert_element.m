% insert element in vector, k can be up to 3
function vector = insert_element(vector, element, k)
    if k >= 1
        vector = runHash1(vector, element);
    end
    if k >= 2
        vector = runHash2(vector, element);
    end
    if k >= 3
        vector = runHash3(vector, element);
    end
end

% auxiliary function to run hash and insert element in vector
function v = runHash1(vector, element)
    hashcode = mod(string2hash(element, 'djb2'), length(vector)) + 1;
    vector(hashcode) = 1;
    v = vector;
end

function v = runHash2(vector, element)
    hashcode = mod(string2hash(element, 'sdbm'), length(vector)) + 1;
    vector(hashcode) = 1;
    v = vector;
end

function v = runHash3(vector, element)
    hashcode = mod(hashstring(element, length(vector)), length(vector)) + 1;
    vector(hashcode) = 1;
    v = vector;
end