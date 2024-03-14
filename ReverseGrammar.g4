grammar ReverseGrammar;

@header{
    package antlr;
}

file_
    : expression* EOF
    ;

expression
    : atom # num
    | op=(TRIG|LOG) LPAREN expression RPAREN # Func
    | expression op=FACTORIAL # Fact
    | expression op=POW expression # Pow
    | MINUS right=expression #Negation
    | expression op=(TIMES | DIV) expression # MulDiv
    | expression op=(PLUS | MINUS) expression # AddSub
    | LPAREN expression RPAREN #paren
    | (PLUS | MINUS)* atom # num
    ;

atom
    : scientific
    ;

scientific
    : SCIENTIFIC_NUMBER
    ;


//The NUMBER part gets its potential sign from "(PLUS | MINUS)* atom" in the expression rule
SCIENTIFIC_NUMBER
    : NUMBER (E SIGN? UNSIGNED_INTEGER)?
    ;

fragment NUMBER
    : ('0' .. '9')+ ('.' ('0' .. '9')+)?
    | 'π'
    | 'e'
    ;

fragment UNSIGNED_INTEGER
    : ('0' .. '9')+
    ;

fragment E
    : 'E'
    | 'e'
    ;

fragment SIGN
    : '+'
    | '-'
    ;

LOG
: ('log'| 'ln')
;

TRIG
    : ('sin'| 'cos'| 'tan'| 'asin'| 'acos'| 'atan'| 'sinh'| 'cosh'| 'tanh')
    ;

FACTORIAL
    : '!'
    ;

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;

PLUS
    : '+'
    ;

MINUS
    : '-'
    ;

TIMES
    : '*'
    ;

DIV
    : '/'
    ;

POW
    : '**'
    ;

INVALID_CHAR : . -> skip ;
WS
    : [ \r\n\t]+ -> skip
    ;