package ba.compiler.minc.intercode.instructions;

public enum Opcode {
    LOAD, // LOAD arg1, null, res ; res = arg1
    ADD,  // ADD arg1, arg2, res  ; res = arg1 OP arg2
    SUB,  // SUB arg1, arg2, res
    MUL,  // MUL arg1, arg2, res
    DIV,  // DIV arg1, arg2, res
    MOD,  // MOD arg1, arg2, res
    LAND, // AND arg1, arg2, res -- logical
    LOR,  // AND arg1, arg2, res -- logical

    NEG,  // NEG arg1, res        ; res = OP arg1
    NOT,  // NOT arg1, res

    CPY,     // CPY arg1, res  ; res = arg1
    IDXCPY,  // IDXCPY arg1, arg2, res ; res = arg1[arg2]
    IDXASG,  // IDXASG arg1, arg2, res ; res[arg1] = arg2

    ISGT,    // IFGT arg1, arg2, R  ; r = arg1 > arg2
    ISGTE,
    ISLT,
    ISLTE,
    ISEQ,
    ISNEQU,

    JUMP,    // JUMP L
    COND,    // COND arg1, L1, L2  ; if true jump L1 else jump L2
    IFT,     // IFT arg1, L   ; if arg1 jump L
    IFF,     // IFF arg1, L   ; if !arg1 jump L

    PARAM,   // PARAM x
    CALL,    // CALL L, n
    CASTi,   // CASTi arg1, res
    CASTc,   // CASTc arg1, res
    CASTr,   // CASTr arg1, res,
    EXIT,    // EXIT arg1   ; exit of function

}
