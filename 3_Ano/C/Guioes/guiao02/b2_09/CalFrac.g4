grammar CalFrac;

program:
    ((stat ';') | NEWLINE)* EOF
    ;

stat:
        print                             #StatPrint
    |   assignment                        #StatAssign
;

print: 'print' expr;

assignment: expr '->' ID;

expr:
        expr '^' op=('+'|'-')? Integer    #ExpExponent
    |   expr op=('*'|':') expr            #ExprMultDiv
    |   expr op=('+'|'-') expr            #ExprAddSub
    |   op=('+'|'-')? Integer '/' Integer #ExprFraction
    |   '(' expr ')'                      #ExprParent
    |   'read "' prompt=.* '"'            #ExprRead
    |   'reduce' expr                     #ExprReduce
    |   Integer                           #ExprInteger
    |   ID                                #ExprID
    ;

Integer: [0-9]+;        // use long integers
ID: [a-zA-Z_]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '//' .*? '\n' -> skip;