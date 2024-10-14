/*
BSD License

Copyright (c) 2013, Tom Everett
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. Neither the name of Tom Everett nor the names of its contributors
   may be used to endorse or promote products derived from this software
   without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

grammar logo;

prog
    : (line? EOL)+ line? EOF
    ;

line
    : cmd+ comment?
    | comment
    | print_ comment?
    | procedureDeclaration
    ;

cmd
    : repeat_
    | fd
    | bk
    | rt
    | lt
    | cs
    | pu
    | pd
    | ht
    | st
    | home
    | label
    | setxy
    | make
    | procedureInvocation
    | ife
    | stop
    | fore
    | arc
    | setpensize
    | setpencolor
    | fill
    | setscreencolor
    ;

//do poprawy
setscreencolor
    : 'scc' expression
    | 'SCC' expression
    | 'setscreencolor' expression
    | 'SETSCREENCOLOR' expression
    ;

fill
    : 'fill'
    | 'FILL'
    ;

setpencolor
    : 'spc' expression
    | 'SPC' expression
    | 'setpencolor' expression
    | 'SETPENCOLOR' expression
    ;

setpensize
    : 'sps' expression
    | 'SPS' expression
    | 'setpensize' expression
    | 'SETPENSIZE' expression
    ;

arc
    : 'arc' expression expression
    | 'ARC' expression expression
    ;

procedureInvocation
    : name expression*
    ;

procedureDeclaration
    : 'to' name parameterDeclarations* EOL? (line? EOL)+ 'end'
    | 'TO' name parameterDeclarations* EOL? (line? EOL)+ 'END'
    ;

parameterDeclarations
    : ':' name (',' parameterDeclarations)*
    ;

func_
    : random
    ;

repeat_
    : 'repeat' number block
    | 'REPEAT' number block
    ;

//block
//    : '[' cmd+ ']'
//    ;

block
    : '[' (cmd WS*)* ']'
    ;

ife
    : 'if' comparison block
    | 'IF' comparison block
    ;

comparison
    : expression comparisonOperator expression
    ;

comparisonOperator
    : '<'
    | '>'
    | '='
    ;

make
    : 'make' STRINGLITERAL value
    | 'MAKE' STRINGLITERAL value
    ;

print_
    : 'print' (value | quotedstring)
    | 'PRINT' (value | quotedstring)
    ;

quotedstring
    : '[' (quotedstring | ~ ']')* ']'
    ;

name
    : STRING
    ;

value
    : STRINGLITERAL
    | expression
    | deref
    ;

signExpression
    : (('+' | '-'))* (number | deref | func_)
    ;

multiplyingExpression
    : signExpression (('*' | '/') signExpression)*
    ;

expression
    : multiplyingExpression (('+' | '-') multiplyingExpression)*
    ;

deref
    : ':' name
    ;

fd
    : ('fd' | 'forward' | 'FD' | 'FORWARD') expression
    ;

bk
    : ('bk' | 'backward' | 'BK' | 'BACKWARD') expression
    ;

rt
    : ('rt' | 'right' | 'RT' | 'RIGHT') expression
    ;

lt
    : ('lt' | 'left' | 'LT' | 'LEFT') expression
    ;

cs
    : 'cs'
    | 'clearscreen'
    | 'CS'
    | 'CLEARSCREEN'
    ;

pu
    : 'pu'
    | 'penup'
    | 'PU'
    | 'PENUP'
    ;

pd
    : 'pd'
    | 'pendown'
    | 'PD'
    | 'PENDOWN'
    ;

ht
    : 'ht'
    | 'hideturtle'
    | 'HT'
    | 'HIDETURTLE'
    ;

st
    : 'st'
    | 'showturtle'
    | 'ST'
    | 'SHOWTURTLE'
    ;

home
    : 'home'
    | 'HOME'
    ;

stop
    : 'stop'
    | 'STOP'
    ;

label
    : 'label'
    | 'LABEL'
    ;

setxy
    : 'setxy' expression expression
    | 'SETXY' expression expression
    ;

random
    : 'random' expression
    | 'RANDOM' expression
    ;

fore
    : 'for' '[' name expression expression expression ']' block
    | 'FOR' '[' name expression expression expression ']' block
    ;

number
    : NUMBER
    ;

comment
    : COMMENT
    ;

STRINGLITERAL
    : '"' STRING
    ;

STRING
    : [a-zA-Z] [a-zA-Z0-9_]*
    ;

NUMBER
    : [0-9]+
    ;

COMMENT
    : ';' ~ [\r\n]*
    ;

EOL
    : '\r'? '\n'
    ;

WS
    : [ \t\r\n]+ -> skip
    ;