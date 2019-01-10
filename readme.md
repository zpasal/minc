# DaCompiler

Source
-> Lexer
-> Parser
-> Static Checker
-> Intermediate Code Generation
-> Optimisation
-> Code Generation


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



