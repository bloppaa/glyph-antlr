grammar Expr;

@header {
package antlr;
}

prog: ((decl | expr | assign | print) ';' | (cond | for))+ EOF # Program;

decl: (INT_TYPE | FLOAT_TYPE | BOOL_TYPE | STR_TYPE) ID '=' expr # Declaration;

assign: ID '=' expr # Assignment;

cond:
	'if' '(' expr ')' '{' block '}' ('else' '{' block '}')? # Condition;

block: statement*;

for:
	'from' '(' ID '=' expr 'to' expr ('step' expr)? ')' '{' block '}' # ForLoop;

print: 'print(' expr ')';

statement:
	decl ';'
	| expr ';'
	| assign ';'
	| print ';'
	| cond
	| for;

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
	| INT									# Int
	| FLOAT									# Real
	| BOOL									# Boolean
	| STR									# String;

INT_TYPE: 'int';
FLOAT_TYPE: 'float';
BOOL_TYPE: 'bool';
STR_TYPE: 'string';
INT: '0' | [1-9][0-9]*;
FLOAT: ('0' | [1-9][0-9]*) ('.' [0-9]+)?;
BOOL: 'true' | 'false';
STR: '"' .*? '"';
ID: [a-z][a-zA-Z0-9_]*;
WS: [ \t\n\r]+ -> skip;
COMMENT: '//' ~[\r\n]* -> skip;