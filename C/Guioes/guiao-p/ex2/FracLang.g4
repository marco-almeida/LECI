grammar FracLang;
program: (stat ';')* EOF;

stat:
	'display' fraction	# statDisplay
	| ID '<=' fraction	# statAssigment;

fraction:
	sign = ('+' | '-') fraction			# fractionSign
	| '(' fraction ')'					# fractionParenthesis
	| f1 = fraction '*' f2 = fraction	# fractionMultiplication
	| f1 = fraction ':' f2 = fraction	# fractionDivision
	| f1 = fraction '-' f2 = fraction	# fractionSubtraction
	| f1 = fraction '+' f2 = fraction	# fractionSum
	| 'read' LITERAL_STRING				# fractionRead
	| 'reduce' fraction					# fractionReduce
	| ID								# fractionId
	| NUM								# fractionNum
	| NUM '/' NUM						# fractionFraction;

LITERAL_STRING: '"' .*? '"';
ID: [a-z]+;
NUM: [0-9]+;
WS: [ \t\r\n]+ -> skip;
COMMENT: '--' .*? '\n' -> skip;