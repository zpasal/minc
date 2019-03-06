package ba.compiler.minc.intercode;

import ba.compiler.minc.idents.Types;
import ba.compiler.minc.intercode.instructions.*;
import ba.compiler.minc.parser.MinCLexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntermediateCode {
    private List<QuadCode> instructions = new ArrayList<>();
    private int tempIndex = 0;
    private Map<Integer, String> labels = new HashMap<>();

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


    public int getTempIndex() {
        return tempIndex;
    }

    public List<QuadCode> getInstructions() {
        return instructions;
    }

    public ArgLabel newLabel(int address, String name) {
        labels.put(address, name);
        return new ArgLabel(address);
    }

    public int getNextInstruction() {
        return instructions.size();
    }

    public String getLabelAt(int address) {
        return labels.get(address);
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
}
