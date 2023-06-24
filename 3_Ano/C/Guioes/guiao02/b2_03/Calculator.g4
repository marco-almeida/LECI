grammar Calculator;

program:
    stat* EOF
    ;

stat: expr? NEWLINE
    ;

expr:
        op=('+'|'-') expr               #ExprPosNeg
    |   expr op=('*'|'/'|'%') expr      #ExprMultDivMod
    |   expr op=('+'|'-') expr          #ExprAddSub
    |   Integer                         #ExprInteger
    |   '(' expr ')'                    #ExprParent
    ;

Integer: [0-9]+;        // use long integers
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;