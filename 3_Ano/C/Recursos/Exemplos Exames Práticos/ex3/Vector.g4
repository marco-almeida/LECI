grammar Vector;

program: (stat ';')* EOF;

stat: 'show' expr # statShow | expr '->' ID # statAssigment;

expr:
	sign = ('+' | '-') expr					# exprUnary
	| e1 = expr '*' e2 = expr				# exprMultiply // verificar semanticamente
	| e1 = expr '.' e2 = expr				# exprInternalProduct // verificar semanticamente
	| e1 = expr op = ('+' | '-') e2 = expr	# exprSumSub // verificar semanticamente
	| '(' expr ')'							# exprParenthesis
	| VECTOR								# exprVector
	| NUMBER								# exprNumber
	| ID									# exprId;

VECTOR: '[' NUMBER (',' NUMBER)* ']';
NUMBER: INT | FLOAT;
INT: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+;
ID: [a-z][a-z0-9]*;
WS: [ \t\r\n]+ -> skip;
COMMENT: '#' .*? '\r'? '\n' -> skip;
ERROR: .;