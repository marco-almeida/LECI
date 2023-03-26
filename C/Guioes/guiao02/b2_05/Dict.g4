grammar Dict;
program: stat+ EOF; // one or more repetitions of stat

stat :
    expr NEWLINE // expr followed by an end-of-line
    ;

expr :
    Integer ' - ' Word;

Integer: [0-9]+ ;
Word: [a-z]+ ;
NEWLINE:    '\r'?   '\n';
WS: [ \t]+ -> skip;