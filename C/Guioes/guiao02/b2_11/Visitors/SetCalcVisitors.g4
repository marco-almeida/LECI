grammar SetCalcVisitors;
main: stat* EOF;
stat: expr;
expr:
	e1 = expr '\\' e2 = expr	# ExprSubtract
	| e1 = expr '&' e2 = expr	# ExprIntersect
	| e1 = expr '+' e2 = expr	# ExprUnion
	| '(' e = expr ')'			# ExprParen
	| set						# ExprSet
	| VAR '=' e = expr			# ExprAssign
	| VAR						# ExprVar;

set: '{' ( elem ( ',' elem)*)? '}';
elem: WORD | NUM;


WORD: [a-z]+;
NUM: [-+]? [0-9]+; // . means any character
VAR: [A-Z]+; // .* means any number of characters
COMMENT:
	'--' .*? '\n' -> skip; // .*? means any number of characters that to not
WS: [ \t\r\n]+ -> skip; // match what comes next
ERROR: .; // to convert all lexer errors into parser errors
