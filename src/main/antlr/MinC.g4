grammar MinC;

@header {
package ba.compiler.minc.parser;
}

compilationUnit:
    declarationItem* EOF
    ;

declarationItem
    : varDeclaration
    | funcDeclaration
    ;

varDeclaration:
    VarKeyword varInnerDeclaration Semicolon
    ;

varInnerDeclaration:
    Identifier Colon type
    ;

type:
    baseType=(IntType | CharType | BoolType | VoidType | RealType) dimensions?
    ;

dimensions:
    ( '[' IntegerLiteral ']' )+
    ;

funcDeclaration:
    FunctionKeyword Identifier LParen paramDefinitions? RParen Colon type functionBlock
    ;

paramDefinitions:
    varInnerDeclaration ( Comma varInnerDeclaration )*
    ;

functionBlock:
    LBlock varDeclaration* statement* RBlock
    ;

block:
    LBlock statement* RBlock
    ;

statement
    : assignmentStatement
    | functionCallStatement
    | ifStatement
    | whileStatement
    | returnStatement
    ;

assignmentStatement:
    lValue Assign expression Semicolon
    ;

lValue:
    Identifier indexes?
    ;

functionCallStatement:
    Identifier LParen exprList? RParen Semicolon
    ;

ifStatement:
    IfKeyword LParen expression RParen block
    ;

whileStatement:
    WhileKeyword LParen expression RParen block
    ;

returnStatement:
    ReturnKeyword expression Semicolon
    ;

exprList:
    expression ( Comma expression )*
    ;

expression
    : Minus expression                                      #unaryMinusExpression
    | Not expression                                        #notExpression
    | expression op=( Mul | Div | Mod ) expression          #multExpression
    | expression op=( Plus | Minus ) expression             #addExpression
    | expression op=( GtE | LtE | Gt | Lt ) expression      #compExpression
    | expression op=( Equ | Nequ ) expression               #eqExpression
    | expression And expression                             #andExpression
    | expression Or expression                              #orExpression
    | IntegerLiteral                                        #integerExpression
    | RealLiteral                                           #realExpression
    | Bool                                                  #boolExpression
    | functionCall indexes?                                 #functionCallExpression
    | Identifier indexes?                                   #identifierExpression
//    | String indexes?
    | LParen expression RParen                              #expressionExpression
;

functionCall:
    Identifier LParen exprList? RParen
    ;

indexes:
    ( '[' expression ']' )+
    ;


// Keywords
VarKeyword:         'var';
FunctionKeyword:    'func';
IfKeyword:          'if';
WhileKeyword:       'while';
ReturnKeyword:      'return';

// Base types
IntType:    'int';
BoolType:   'bool';
CharType:   'char';
RealType:   'real';
VoidType:   'void';

Colon:      ':' ;
Semicolon:  ';' ;
Comma:      ',' ;
LParen:     '(' ;
RParen:     ')' ;
LBlock:     '{' ;
RBlock:     '}' ;
Assign:     '=' ;
Mul:        '*' ;
Div:        '/' ;
Mod:        '%' ;
Plus:       '+' ;
Minus:      '-' ;
Gt:         '>' ;
GtE:        '>=';
Lt:         '<' ;
LtE:        '<=';
Equ:        '==' ;
Nequ:       '!=' ;
And:        '&&' ;
Or:         '||' ;
Not:        '!'  ;

Identifier:  ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;

Bool:           'true' | 'false' ;
IntegerLiteral: ('0'..'9')+;
RealLiteral: IntegerLiteral '.' IntegerLiteral* ;

Whitespace:
    [ \t]+
        -> skip
    ;
Newline:
    ('\r' '\n'? | '\n' )
        -> skip
    ;