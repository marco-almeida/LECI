grammar CalculatorMain;

program:
    stat* EOF
    ;

stat: (expr | assignment)? NEWLINE
    ;

assignment: ID '=' expr;

expr:
        op=('+'|'-') expr               #ExprPosNeg
    |   expr op=('*'|'/'|'%') expr      #ExprMultDivMod
    |   expr op=('+'|'-') expr          #ExprAddSub
    |   Integer                         #ExprInteger
    |   ID                              #ExprID
    |   '(' expr ')'                    #ExprParent
    ;

Integer: [0-9]+;        // use long integers
ID: [a-zA-Z_]+;         
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;