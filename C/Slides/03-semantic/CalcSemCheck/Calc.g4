grammar Calc;

program: (stat ';')* EOF;

stat: show | declaration | assignment;

show:
     'show' expr
    ;

declaration: idList ':' type;

idList locals[Type t = null]:
        ID (',' ID)*
      ;

type returns[Type res=null]:
      'integer' #IntegerType
    | 'real'    #RealType
    ;

assignment: expr '->' ID;

expr returns[int v=0,Type t = null]:
      e1=expr op=('*' | '/' | '//' | '\\\\') e2=expr #ExprMultDiv
    | e1=expr op=('+' | '-') e2=expr                 #ExprAddSub
    | sign=('+'|'-')? INT                            #ExprInt
    | sign=('+'|'-')? REAL                           #ExprReal
    | ID                                             #ExprID
    | '(' e=expr ')'                                 #ExprParen
    ;

INT: [0-9]+;
REAL: INT'.'INT;
ID: [a-z]+;
LINE_COMMENT: '--' .*? '\r'? '\n' -> skip;
WS: [ \t\r\n]+ -> skip;
ERROR: .;

