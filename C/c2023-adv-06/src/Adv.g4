grammar Adv;

program: alphabet structs+;

structs: automaton+ view+ animation+ play;

play: 'play' NAME;

animation: decl_animation '<<<' animation_body '>>>';

decl_animation: 'animation' NAME;

animation_body: viewport+ viewport_description+;

viewport_description: 'on' NAME '<<<' viewport_commands+ '>>>';

viewport_commands: show_command | pause | for_loop_viewport;

pause: 'pause' ';';

show_command: 'show' showables (',' showables)* ';';

showables: (
		NAME (
			'[' key = ('initial' | 'accepting') '=' value = (
				'true'
				| 'false'
			) ']'
		)?
	)
	| transition;

viewport:
	'viewport' vpname = NAME 'for' viewname = NAME 'at' cart_vector '--' '++' cart_vector ';';

view: decl_view '<<<' view_body '>>>';

view_body: (
		place_statement ';'
		| multi_point_declaration ';'
		| point_decl_assign ';'
		| point_assignment ';'
		| transition_arrow_statement ';'
		| grid_statement ';'
		| view_transition ';'
	)+;

grid_statement:
	'grid' NAME '(' width = INT ',' height = INT ')' keypair_properties;

transition_arrow_statement:
	transition 'as' point arrow_type = ('--' | '..') point (
		arrow_type = ('--' | '..') point
	)*;

multi_point_declaration: 'point' NAME (',' NAME)*;

point_decl_assign: 'point' NAME '=' point;

point_assignment: NAME '=' point;

point: ('(' state = NAME ')') | vector | expr;

place_statement:
	'place' placeable 'at' place_in (',' placeable 'at' place_in)*;

place_in: vector | NAME;

placeable: NAME | view_transition;

keypair_properties: '[' key_values ']';

key_values: key_value (',' key_value)*;

key_value: key = NAME '=' value = keypair_value+;

view_transition: transition '#label' keypair_properties;

decl_view: 'view' vpname = NAME 'of' automname = NAME;

automaton: decl_automaton '<<<' automaton_body '>>>';

automaton_body:
	declare_states+ (change_state_property | for_loop_state)* state_transitions;

for_loop_viewport:
	'for' elem = NAME 'in' '{' list '}' (
		viewport_commands
		| ('<<<' viewport_commands* '>>>')
	);

automaton_commands:
	automaton_transition
	| change_state_property
	| declare_states;

for_loop_state:
	'for' elem = NAME 'in' '{' list '}' (
		automaton_commands
		| ('<<<' automaton_commands* '>>>')
	);

list: '{' NAME (',' NAME)* '}';

state_transitions:
	'transition' automaton_transition (',' automaton_transition)* ';';

automaton_transition: NAME '->' SYMBOL (',' SYMBOL)* '->' NAME;

change_state_property:
	NAME '[' key = ('initial' | 'accepting') '=' value = (
		'true'
		| 'false'
	) ']' ';';

declare_states: 'state' NAME (',' NAME)* ';';

decl_automaton: automaton_types NAME;

alphabet: 'alphabet' '{' SYMBOL (',' SYMBOL)* '}';

transition: '<' NAME ',' NAME '>';

vector: cart_vector | polar_vector;

cart_vector: '(' expr ',' expr ')';

polar_vector: '(' expr ':' expr ')';

expr
	returns[Type t = null]:
	'(' expr ')'					# exprParenthesis
	| expr op = ('*' | '/') expr	# exprMulDiv
	| expr op = ('+' | '-') expr	# exprAddSub
	| op = ('-' | '+') expr			# exprPositiveNegative
	| number						# exprNumber
	| vector						# exprVector
	| NAME							# exprName;

keypair_value: number | NAME;

number: INT | REAL;

automaton_types: 'NFA' | ('complete'? 'DFA');

STR: '"' .*? '"';

INT: [0-9]+;

REAL: [0-9]* '.' [0-9]+;

LINE_COMMENT: '//' .*? '\n' -> skip;
COMMENT: '/*' .*? '*/' -> skip;

NAME: [a-zA-Z_][a-zA-Z_0-9]*;

SYMBOL: '\'' [0-9a-zA-Z]'\'';

WS: [ \t\r\n]+ -> skip;
