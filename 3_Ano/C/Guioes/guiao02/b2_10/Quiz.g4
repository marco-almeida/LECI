grammar Quiz;

program: quiz* EOF;

quiz: quizHeader '{' NEWLINE answer+ '}' NEWLINE+;

quizHeader: QUESTION_ID '(' QUOTED_STRING ')';
answer: QUOTED_STRING ':' INTEGER ';' NEWLINE;

QUOTED_STRING:
	'"' (ESC | .)*? '"'; // match anything between quotes
ESC: '\\"' | '\\\\'; // unescape quotes and backslashes
INTEGER: [0-9]+;
QUESTION_ID: [A-Za-z0-9.]+;
NEWLINE: '\r'? '\n';
COMMENT: '#' .*? '\n' -> skip;
WS: [ \t] -> skip; // WS: [ \t]+ -> skip;