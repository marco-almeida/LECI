grammar Calculator;

main: stat* EOF;

stat: show | assignment;

show: expr;

assignment: ID '=' expr;

expr:
    op=('+'|'-') e2=expr          #ExprUnaryOp
  | e1=expr op=('*'|'/') e2=expr  #ExprMultDiv
  | e1=expr op=('+'|'-') e2=expr  #ExprAddSub
  | '(' expr ')'                  #ExprParent
  | ID                            #ExprID
  | Number                        #ExprNumber
  ;

Number: [0-9]+;
ID: [a-zA-Z_][a-zA-Z_0-9]*;
WhiteSpace: [ \t\r\n]+ -> skip;
Error: .;
