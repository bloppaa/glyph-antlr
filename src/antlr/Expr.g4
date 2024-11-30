grammar Expr;

@header {
package antlr;
}

prog: (
		(decl | expr | assign | print) SEMICOLON
		| (cond | forLoop | whileLoop | func)
	)+ EOF # Program;

decl: type ID ASSIGN expr # Declaration;

type: INT_TYPE | FLOAT_TYPE | BOOL_TYPE | STR_TYPE;

assign: ID ASSIGN expr # Assignment;

cond:
	IF LPAREN expr RPAREN LCURLY block RCURLY (
		ELSE LCURLY block RCURLY
	)? # Condition;

func:
	FUNC_TYPE ID LPAREN params? RPAREN LCURLY block RCURLY # Function;

params: type ID (COMMA type ID)* # Parameters;

args: expr (COMMA expr)* # Arguments;

forLoop:
	FOR LPAREN ID ASSIGN expr TO expr (STEP expr)? RPAREN LCURLY block RCURLY;

whileLoop: WHILE LPAREN expr RPAREN LCURLY block RCURLY;

print: PRINT LPAREN expr RPAREN;

return: RETURN expr;

block: statement*;

statement:
	(decl | expr | assign | print | return) SEMICOLON
	| (cond | forLoop | whileLoop);

expr:
	LPAREN expr RPAREN					# Parens
	| MINUS expr						# UnaryMinus
	| NOT expr							# Not
	| expr (MULT | DIV | MOD) expr		# MultDivMod
	| expr (PLUS | MINUS) expr			# AddSub
	| expr (GT | GTE | LT | LTE) expr	# Comparison
	| expr (EQ | NEQ) expr				# Equality
	| expr AND expr						# And
	| expr OR expr						# Or
	| ID LPAREN args? RPAREN			# FunctionCall
	| ID								# Variable
	| INT								# Int
	| FLOAT								# Real
	| BOOL								# Boolean
	| STR              					# String;

SEMICOLON: '✋';
COMMA: '🔸';
ASSIGN: '👉';
NOT: '🙅';
AND: '🤝';
OR: '🤷';
PLUS: '➕';
MINUS: '➖';
MULT: '✖️';
DIV: '➗';
MOD: '🪙';
EQ: '🟰';
NEQ: '🚫';
GT: '▶️';
GTE: '⏩';
LT: '◀️';
LTE: '⏪';
LPAREN: '🧎‍➡️';
RPAREN: '🧎';
LCURLY: '🏃‍➡️';
RCURLY: '🏃';
IF: '🤔';
ELSE: '👇';
FOR: '🔁';
TO: '➡️';
STEP: '👟';
WHILE: '🌀';
PRINT: '🖨️';
RETURN: '↩️';
INT_TYPE: '🧮';
FLOAT_TYPE: '🛟';
BOOL_TYPE: '✅';
STR_TYPE: '📄';
FUNC_TYPE: '🍿';
INT: '0' | [1-9][0-9]*;
FLOAT: ('0' | [1-9][0-9]*) ('.' [0-9]+)?;
BOOL: '👍' | '👎';
STR_DLM: '🧵';
STR: STR_DLM .*?  STR_DLM; 
COMMENT: '💭' ~[\r\n]* -> skip;
ID: [a-z][a-zA-Z0-9_]*;
WS: [ \t\n\r]+ -> skip;