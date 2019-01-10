# MinC - Minimal Compiler

Very basic language built for testing optimisations algorithms described in `Building Optimizing Compiler by Robert Morgan`


# Overview

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/passes.png?sanitize=true&raw=true" />


Multi pass compiler (no compilation speed optimizations). Using ANTL4 for quick building Lexer and Parser.

## Syntax

```
func max(a:real, b:real) : real {
    if  (a > b) { return a; }
    return b;
}

func sum(a:int[10]) : int {
var sum : int;
var i : int;
    sum = 0;
    i = 0;
    while(i<10) {
        sum = sum + a[i];
    }
    return sum;
}
```

Check src/main/antlr/MinC.g4 for full grammar description.

## ANTLR Parse Tree

Lexer and Parser are generated with antlr (gradle plugin). Generated files can be found in ba.compiler.minc.parser package. When ANTLR parser runs it will generate Parse Tree e.g. for simple program implementing function max, parse tree looks like: 

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/parseTree.png?sanitize=true&raw=true" />

## Abstrct Syntax tree

Nest step is to translate Parse Tree to AST ... cleaning unneccessery parse declarations that are not needed for furhter code processing e.g.:

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/parsetreeast.png?sanitize=true&raw=true" />

Code for defining and translating Parse Tree to AST can be found in ba.compiler.minc.ast package.

## Ident table and types

Second pass of comipler will collect all identifier (variable declarations) and store them in Identifier Table, keeping track on different scopes - see ba.compiler.minc.idents.

## Intermediate Code Generation

Next pass is to build Intermediate Code (ba.compiler.minc.intercode). ICG is based on "Dragon Book" - `Compilers: Principles, Techniques, and Tools" by Alfred V. Aho, Monica S. Lam, Ravi Sethi, and Jeffrey D. Ullman`. 

Basic set of abstract instructions:

```
;; res = arg1 OP arg2
ADD arg1, arg2, res
SUB arg1, arg2, res
MUL arg1, arg2, res
DIV arg1, arg2, res
MOD arg1, arg2, res

;; res = OP arg1
NEG arg1, res
CAST arg1, res

;; Assignment
CPY arg1, res
IDXCPY arg1, arg2, res   ; res = arg1[arg2]
IDXASG arg1, arg2, res  ; res[arg1] = arg2

;; Flow
JUMP L
IFT arg1, L
IFF arg1, L
IFGT arg1, arg2, L
IFGTE arg1, arg2, L
IFLT arg1, arg2, L
IFLTE arg1, arg2, L
IFEQ  arg1, arg2, L
IFNEQU arg1, arg, L

;; Function call
PARAM arg1
CALL P, N
```
## Optimisations
Comming soon



