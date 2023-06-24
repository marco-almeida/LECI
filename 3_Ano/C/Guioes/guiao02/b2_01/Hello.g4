grammar Hello; // define grammar called Hello
top: greetings | bye;
bye: 'goodbye' name;
greetings:
	'hello' name; // match keyword hello followed by an identifier
name: ID+;
ID: [a-zA-Z]+; // match lower-case identifiers
WS:
	[ \t\r\n]+ -> skip; // skip spaces, tabs, newlines, \r (windows)