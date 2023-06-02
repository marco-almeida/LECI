grammar Calc;

main: stat* EOF;

stat: expr;

expr returns[String var = null]:
    expr op=('*'|'/') expr  #ExprMultDiv
  | expr op=('+'|'-') expr  #ExprAddSub
  | '(' expr ')'            #ExprParent
  | Number                  #ExprNumber
  ;

Number: [0-9]+;
WS: [ \t\r\n]+ -> skip;
ERROR: .;
