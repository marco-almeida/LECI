grammar StrLang;

program: stat* EOF;

stat:
	'print' printable			# statPrint
	| VARIABLE ':' printable	# statAssign;

printable:
	printable '+' printable									# printableConcat
	| printable '-' printable								# printableRemove
	| 'trim' printable										# printableTrim
	| '(' printable ')'										# printableParenthesis
	| p1 = printable '/' p2 = printable '/' p3 = printable	# printableSubstitution
	| 'input' '(' printable ')'								# printableInput
	| VARIABLE												# printableVariable
	| QUOTED_STR											# printableString;

QUOTED_STR: '"' .*? '"';
VARIABLE: [A-Za-z][A-Za-z0-9]*;
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' .*? '\n' -> skip;