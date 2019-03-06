# MinC - Minimal Compiler

Very basic language built for testing optimisations algorithms described in `Building Optimizing Compiler by Robert Morgan`


# Overview

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/passes.png?sanitize=true&raw=true" />


Multi pass compiler (no compilation speed optimizations). Using ANTL4 for quick building Lexer and Parser.

## Example MinC code

```
func largestColumn(a:int[10][10], n:int, large:int[10], value:int[10]) : void {
    var i : int;
    var j : int;
    i = 0;

    while(i < n) {
        large[i] = 1;
        value[i] = a[1][i];

        j = 2;
        while(j < n) {
            if (a[j][i] > value[i]) {
                value[i] = a[j][i];
                large[i] = j;
            }
            j = j + 1;
        }

        i = i + 1;
    }
}
```


## ANTLR Parse Tree

Lexer and Parser are generated with antlr (using gradle plugin). Code is generated from grammar decribed in `src/main/antlr/MinC.g4` Generated files can be found in ba.compiler.minc.parser package. 

When ANTLR parser runs it will generate Parse Tree. For example, for simple program implementing function max, parse tree looks like: 

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/parseTree.png?sanitize=true&raw=true" />

## Abstrct Syntax tree

Nest step is to translate Parse Tree to AST ... cleaning unneccessery parse declarations that are not needed for furhter code processing e.g.:

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/parsetreeast.png?sanitize=true&raw=true" />

Code for defining and translating Parse Tree to AST can be found in ba.compiler.minc.ast package.

## Ident table and types

Second pass of compiler will collect all identifier (variable declarations) and store them in Identifier Table, keeping track on different scopes - see ba.compiler.minc.idents.

## Intermediate Code Generation

Next pass is to build Intermediate Code (ba.compiler.minc.intercode). IR form is lowered to look like targeted machine
form but keeping some higher level instructions (like indexing).

Basic set of abstract instructions:

```
    LOAD, // LOAD arg1, null, res           ; res = arg1
    ADD,  // ADD arg1, arg2, res            ; res = arg1 OP arg2
    SUB,  // SUB arg1, arg2, res
    MUL,  // MUL arg1, arg2, res
    DIV,  // DIV arg1, arg2, res
    MOD,  // MOD arg1, arg2, res
    LAND, // AND arg1, arg2, res            ; logical and
    LOR,  // AND arg1, arg2, res            ; logical or

    NEG,  // NEG arg1, res                  ; res = OP arg1
    NOT,  // NOT arg1, res

    CPY,     // CPY arg1, res               ; res = arg1
    IDXCPY,  // IDXCPY arg1, arg2, res      ; res = arg1[arg2]
    IDXASG,  // IDXASG arg1, arg2, res      ; arg1[arg2] = res

    ISGT,    // IFGT arg1, arg2, R          ; r = arg1 > arg2
    ISGTE,
    ISLT,
    ISLTE,
    ISEQ,
    ISNEQU,

    JUMP,    // JUMP L
    COND,    // COND arg1, L1, L2           ; if true jump L1 else jump L2
    IFT,     // IFT arg1, L                 ; if arg1 jump L
    IFF,     // IFF arg1, L                 ; if !arg1 jump L

    PARAM,   // PARAM x
    CALL,    // CALL L, n
    CASTi,   // CASTi arg1, res             ; res = (int)arg1
    CASTc,   // CASTc arg1, res             ; res = (char)arg1
    CASTr,   // CASTr arg1, res             ; res = (real)arg1
    EXIT,    // EXIT arg1                   ; exit of function
```

Generated code (without any optimisations) for largestColumn function looks like:

```
	0000000 LOAD      0, T1
	0000001 CPY       T1, i
L_WHILE_E_2:
	0000002 LOAD      i, T2
	0000003 LOAD      n, T3
	0000004 ISLT      T2, T3, T4
	0000005 COND      T4, L_WHILE_T_6, L_WHILE_F_51
L_WHILE_T_6:
	0000006 LOAD      0, T5
	0000007 LOAD      i, T6
	0000008 IDXASG    large, T6, T5
	0000009 LOAD      0, T7
	0000010 LOAD      i, T8
	0000011 MUL       T7, 10, T10
	0000012 ADD       T8, T10, T11
	0000013 IDXCPY    a, T11, T9
	0000014 LOAD      i, T12
	0000015 IDXASG    value, T12, T9
	0000016 LOAD      1, T13
	0000017 CPY       T13, j
L_WHILE_E_18:
	0000018 LOAD      j, T14
	0000019 LOAD      n, T15
	0000020 ISLT      T14, T15, T16
	0000021 COND      T16, L_WHILE_T_22, L_WHILE_F_46
L_WHILE_T_22:
	0000022 LOAD      j, T17
	0000023 LOAD      i, T18
	0000024 MUL       T17, 10, T20
	0000025 ADD       T18, T20, T21
	0000026 IDXCPY    a, T21, T19
	0000027 LOAD      i, T22
	0000028 LOAD      value, T22, T23
	0000029 ISGT      T19, T23, T24
	0000030 COND      T24, L_IF_T_31, L_IF_F_41
L_IF_T_31:
	0000031 LOAD      j, T25
	0000032 LOAD      i, T26
	0000033 MUL       T25, 10, T28
	0000034 ADD       T26, T28, T29
	0000035 IDXCPY    a, T29, T27
	0000036 LOAD      i, T30
	0000037 IDXASG    value, T30, T27
	0000038 LOAD      j, T31
	0000039 LOAD      i, T32
	0000040 IDXASG    large, T32, T31
L_IF_F_41:
	0000041 LOAD      j, T33
	0000042 LOAD      1, T34
	0000043 ADD       T33, T34, T35
	0000044 CPY       T35, j
	0000045 JUMP      L_WHILE_E_18
L_WHILE_F_46:
	0000046 LOAD      i, T36
	0000047 LOAD      1, T37
	0000048 ADD       T36, T37, T38
	0000049 CPY       T38, i
	0000050 JUMP      L_WHILE_E_2
L_WHILE_F_51:
	0000051 EXIT

```

Nest ste is to define Basic Blocks and to build Flow Graph.

### Basic Blocks

Following statements of the code will always be called as leaders:

1. First statement of the code
2. Statement that is a target of the conditional goto statement or unconditional goto statement
3. Statement that appears immediately after a goto statement


All the statements that follows the leader (including the leader itself) till just before
the next leader appears forms one basic block.

Basic Blocks built from previous IC sample:

```
BB-0:
	0000000 LOAD      0, T1
	0000001 CPY       T1, (i)
BB-2:
	0000002 LOAD      (i), T2
	0000003 LOAD      (n), T3
	0000004 ISLT      T2, T3, T4
	0000005 COND      T4, @6, @51
BB-6:
	0000006 LOAD      0, T5
	0000007 LOAD      (i), T6
	0000008 IDXASG    (large), T6, T5
	0000009 LOAD      0, T7
	0000010 LOAD      (i), T8
	0000011 MUL       T7, 10, T10
	0000012 ADD       T8, T10, T11
	0000013 IDXCPY    (a), T11, T9
	0000014 LOAD      (i), T12
	0000015 IDXASG    (value), T12, T9
	0000016 LOAD      1, T13
	0000017 CPY       T13, (j)
BB-18:
	0000018 LOAD      (j), T14
	0000019 LOAD      (n), T15
	0000020 ISLT      T14, T15, T16
	0000021 COND      T16, @22, @46
BB-22:
	0000022 LOAD      (j), T17
	0000023 LOAD      (i), T18
	0000024 MUL       T17, 10, T20
	0000025 ADD       T18, T20, T21
	0000026 IDXCPY    (a), T21, T19
	0000027 LOAD      (i), T22
	0000028 LOAD      (value), T22, T23
	0000029 ISGT      T19, T23, T24
	0000030 COND      T24, @31, @41
BB-31:
	0000031 LOAD      (j), T25
	0000032 LOAD      (i), T26
	0000033 MUL       T25, 10, T28
	0000034 ADD       T26, T28, T29
	0000035 IDXCPY    (a), T29, T27
	0000036 LOAD      (i), T30
	0000037 IDXASG    (value), T30, T27
	0000038 LOAD      (j), T31
	0000039 LOAD      (i), T32
	0000040 IDXASG    (large), T32, T31
BB-41:
	0000041 LOAD      (j), T33
	0000042 LOAD      1, T34
	0000043 ADD       T33, T34, T35
	0000044 CPY       T35, (j)
	0000045 JUMP      @18
BB-46:
	0000046 LOAD      (i), T36
	0000047 LOAD      1, T37
	0000048 ADD       T36, T37, T38
	0000049 CPY       T38, (i)
	0000050 JUMP      @2
BB-51:
	0000051 EXIT
```

### Flow Graph

Once an intermediate-code program is partitioned into basic blocks, we represent the flow of
control between them by a flow graph. The nodes of the flow graph are the basic blocks.

Sample flow graph for example above:

<img src="https://raw.githubusercontent.com/zpasal/minc/master/assets/flowGraph.png?sanitize=true&raw=true" />


## Optimisations
Comming soon



