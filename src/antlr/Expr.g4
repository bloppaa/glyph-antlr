grammar Expr;

@header {
package antlr;
}

prog: (
		(decl | expr | assign | print) ';'
		| (cond | forLoop | whileLoop | func)
	)+ EOF # Program;

decl: type ID '=' expr # Declaration;

type: INT_TYPE | FLOAT_TYPE | BOOL_TYPE | STR_TYPE;

assign: ID '=' expr # Assignment;

cond:
	'if' '(' expr ')' '{' block '}' ('else' '{' block '}')? # Condition;

func:
	'func' ID '(' params? ')' (':' type)? '{' block '}' # Function;

params: type ID (',' type ID)* # Parameters;

args: expr (',' expr)* # Arguments;

forLoop:
	'from' '(' ID '=' expr 'to' expr ('step' expr)? ')' '{' block '}';

whileLoop: 'while' '(' expr ')' '{' block '}';

print: 'print(' expr ')';

return: 'return' expr;

block: statement*;

statement:
	(decl | expr | assign | print | return) ';'
	| (cond | forLoop | whileLoop);

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
	| ID '(' args? ')'						# FunctionCall
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