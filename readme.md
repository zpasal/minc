# MinC - Minimal Compiler

Very basic language built for testing optimisations algorithms described in `Building Optimizing Compiler - Robert Morgan`


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

Lexer and Parser are generated with antlr (gradle plugin). When aNtlR parser runs it will generate Parse Tree e.g. for simple program implementing function max, parse tree looks like: 

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/parseTree.png?sanitize=true&raw=true" />


## Abstrct Syntax tree

Nest step is to translate Parse Tree to AST ... whihc cleanses unneccessery parse declarations that are not needed for furhter code processing e.g.:

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/parsetreeast.png?sanitize=true&raw=true" />


## Intermediate Code - Instruction Set

* COPY      x = y
* BINARY    x = y op z
* UNARY     x = op y
* JUMP      goto L
* COND      if x goto L  |  ifFalse x goto L
* COND      if x relop y goto L
* PROCp     param x1
            param xn
            call p, n
* COPYi     x = y[i]
* ADDRESS   x = &y



