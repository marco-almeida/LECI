% 1
% Crie uma funcao para gerar chaves constituıdas por caracteres. O comprimento (i.e., o numero de ca-
% racteres) de cada chave deve ser escolhido aleatoriamente (distribuicao uniforme) entre imin e imax. A
% funcao deve ter como parametros de entrada o ńumero N de chaves a gerar, os valores de imin e imax, um
% vector com os caracteres a usar nas chaves e um vector com as probabilidades de cada um dos caracteres
% a utilizar. Se a funcao for chamada sem o ultimo parametro, deve considerar os caracteres equiprovaveis
% (ver a documentacao da funcao nargin).

% a
% Gere um conjunto de N = 10e5 chaves usando todas as letras maiusculas e minusculas (’A’ a ’Z’ e
% ’a’ a ’z’) com igual probabilidade e em que imin = 6 e imax = 20.
N = 10^5;
imin = 6;
imax = 20;
caracteres = ['A':'Z' 'a':'z'];
prob_caracteres = ones(1, length(caracteres)) / length(caracteres);
chaves = gera_chaves(N, imin, imax, caracteres, prob_caracteres);

% b
% Gere um conjunto de N = 10^5 chaves usando todas as letras minusculas (’a’ a ’z’) com as pro-
% babilidades contidas no ficheiro prob_pt.txt e que correspondem as frequencias das letras em Por-
% tugues (https://pt.wikipedia.org/wiki/Frequ%C3%AAncia_de_letras). Con-
% sidere novamente imin = 6 e imax = 20
prob_caracteres = load('prob_pt.txt');
caracteres = ['a':'z'];
chaves = gera_chaves(N, imin, imax, caracteres, prob_caracteres);

