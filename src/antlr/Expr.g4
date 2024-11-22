grammar Expr;

@header {
package antlr;
}

prog: ((decl | expr | assign) ';')+ EOF # Program;

decl: (INT_TYPE | FLOAT_TYPE | BOOL_TYPE) ID '=' expr # Declaration;

assign: ID '=' expr # Assignment;

expr:
	'(' expr ')'							# Parens
	| '-' expr								# UnaryMinus
	| '!' expr								# Not
	| expr ('*' | '/' | '%') expr			# MultDivMod
	| expr ('+' | '-') expr					# AddSub
	| expr ('>' | '>=' | '<' | '<=') expr	# Comparison
	| expr ('==' | '!=') expr				# Equality
	| expr '&&' expr						# And
	| expr '||' expr						# Or
	| ID									# Variable
	| NUM									# Number
	| BOOL									# Boolean;

INT_TYPE: 'int';
FLOAT_TYPE: 'float';
BOOL_TYPE: 'bool';
BOOL: 'true' | 'false';
ID: [a-z][a-zA-Z0-9_]*;
NUM: ('0' | [1-9][0-9]*) ('.' [0-9]+)?;
WS: [ \t\n\r]+ -> skip;
COMMENT: '//' ~[\r\n]* -> skip;