# MinC - Minimal Compiler

Very basic language built for testing optimisations algorithms described in `Building Optimizing Compiler - Robert Morgan`


# Overview

Multi pass compiler:

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/passes.png?sanitize=true&raw=true" />


## ANTLR4 -> AST


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



