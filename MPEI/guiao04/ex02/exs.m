k = 3;
n = 8000;
numWords = 1000;
vector = init_vector(n);

fileID = fopen('words.txt', 'r');
formatSpec = '%s';
words = textscan(fileID, formatSpec);
words = words{1};
U1 = words(1:numWords);

% insert words into vector

for i = 1:numWords
    vector = insert_element(vector, U1{i}, k);
end

% 2
exists_word = zeros(1, numWords);
false_negatives_U1 = 0;

for i = 1:numWords
    exists_word(i) = isMember(vector, U1{i}, k);

    if exists_word(i) == 0
        false_negatives_U1 = false_negatives_U1 + 1;
    end

end

false_negatives_U1


% 3
U2 = words(numWords + 1:11000);
% check u2 length and u1 length
false_positives_U2 = 0;

for i = 1:numWords
    exists_word(i) = isMember(vector, U2{i}, k);

    if exists_word(i) == 1
        false_positives_U2 = false_positives_U2 + 1;
    end

end

false_positives_U2
fprintf("Percentage of false positives: %4.2f%%", false_positives_U2 / numWords * 100)

% valor otimo de k está entre 5 e 6

