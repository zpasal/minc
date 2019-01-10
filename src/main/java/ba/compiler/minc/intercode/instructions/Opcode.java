package ba.compiler.minc.intercode.instructions;

public enum Opcode {
    ADD,  // ADD arg1, arg2, res  ; res = arg1 OP arg2
    SUB,  // SUB arg1, arg2, res
    MUL,  // MUL arg1, arg2, res
    DIV,  // DIV arg1, arg2, res
    MOD,  // MOD arg1, arg2, res

    NEG,  // NEG arg1, res        ; res = OP arg1
    NOT,  // NOT arg1, res

    CPY,     // CPY arg1, res  ; res = arg1
    IDXCPY,  // IDXCPY arg1, arg2, res ; res = arg1[arg2]
    IDXASG,  // IDXASG arg1, arg2, res ; res[arg1] = arg2

    JUMP,    // JUMP L
    IFT,     // IFT arg1, L   ; if arg1 jump L
    IFF,     // IFF arg1, L   ; if !arg1 jump L
    IFGT,    // IFGT arg1, arg2, L  ; if arg1 > arg2 jump L
    IFGTE,
    IFLT,
    IFLTE,
    IFEQ,
    IFNEQU,

    PARAM,   // PARAM x
    CALL,    // CALL L, n
    CASTi,   // CASTi arg1, res
    CASTc,   // CASTc arg1, res
    CASTr,   // CASTr arg1, res

}
