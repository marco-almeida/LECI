% considere a implementação de um filtro de bloom para representar os elementos de um conjunto de palavras. Considere palavras geradas aleaetoriamente com carateres de 'a' a 'z' (equiprovaveis) e com comprimento de 5 carateres (probabilidade de 40%) ou 8 carateres (probabilidade de 60%).

% assuma que vai adicionar 300 palavras ao filtro de bloom. assuma tambem que o filtro de bloom usa uma unica funçao de dispersao e que devera ter uma taxa de falsos positivos de aproximadamente 3%. determine o tamanho adequado do filtro de bloom e apresente o codigo que usou.

k = 1;
m = 12000;
n = 2^32;
p = (1-(1/n)^(k*m))^k

asd = 1;
for k = 1:1
    N = 10^3;
    caracteres = 'a':'z';
    prob_caracteres = ones(1, length(caracteres)) / length(caracteres);
    chaves = key_gen(N, caracteres, prob_caracteres);
    % primeiras 300 chaves da variavel chaves
    chaves = unique(chaves);
    U1 = chaves(1:300);
    vetor = init_vector(10000);

    for i = 1:length(U1)
        vetor = insert_element(vetor, U1{i}, 1);
    end

    numWords = 300;
    exists_word = zeros(1, numWords);

    false_positives = 0;
    U2 = chaves(301:600);

    for i = 1:numWords
        exists_word(i) = isMember(vetor, U2{i}, 1);

        if exists_word(i) ~= 0
            false_positives = false_positives + 1;
            asd = asd + 1;
        end

    end
end
asd / 1