package ba.compiler.minc.intercode;

import ba.compiler.minc.idents.Types;
import ba.compiler.minc.intercode.instructions.Arg;
import ba.compiler.minc.intercode.instructions.ArgTemp;
import ba.compiler.minc.intercode.instructions.Opcode;
import ba.compiler.minc.intercode.instructions.QuadCode;
import ba.compiler.minc.parser.MinCLexer;

import java.util.ArrayList;
import java.util.List;

public class IntermediateCode {
    private List<QuadCode> instructions = new ArrayList<>();
    private int tempIndex = 0;

    public QuadCode gen(Opcode op, Arg arg1, Arg arg2, Arg res) {
        QuadCode quadCode = new QuadCode(op, arg1, arg2, res);
        instructions.add(quadCode);
        return quadCode;
    }

    public ArgTemp newTemp() {
        return new ArgTemp(++tempIndex);
    }

    public ArgTemp lastResultTemp() {
        return new ArgTemp(tempIndex);
    }


    /*
    *  implicit conversation by widening: double -> float -> long -> int -> char
    *
    * */
    public Arg widen(Arg arg, int typeT, int typeW) {
        if (typeT == typeW) {
            return arg;
        }
        List<Integer> toTypeList = Types.WIDENING_TYPES.get(typeT);
        if (toTypeList == null) {
            throw new RuntimeException("No conversion from " + typeT);
        }

        if (toTypeList.stream().anyMatch(x -> x == typeW)) {
            if (typeW == MinCLexer.CharType) {
                ArgTemp res = newTemp();
                gen(Opcode.CASTc, arg, null, res);
                return res;
            }
            else if (typeW == MinCLexer.IntType) {
                ArgTemp res = newTemp();
                gen(Opcode.CASTi, arg, null, res);
                return res;
            }
            else if (typeW == MinCLexer.RealType) {
                ArgTemp res = newTemp();
                gen(Opcode.CASTr, arg, null, res);
                return res;
            }
            else {
                throw new RuntimeException("No instruction for cast type " + typeW);
            }
        }
        else {
            throw new RuntimeException("No conversion to " + typeW);
        }
    }

    public void genLabel() {

    }

    public int getTempIndex() {
        return tempIndex;
    }

    public List<QuadCode> getInstructions() {
        return instructions;
    }
}
