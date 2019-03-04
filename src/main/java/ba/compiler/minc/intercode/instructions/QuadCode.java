package ba.compiler.minc.intercode.instructions;

import java.util.ArrayList;
import java.util.List;

public class QuadCode {

    public Opcode operand;
    public Arg arg1;
    public Arg arg2;
    public Arg result;

    public QuadCode(Opcode operand, Arg arg1, Arg arg2, Arg result) {
        this.operand = operand;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    public Opcode getOperand() {
        return operand;
    }

    public void setOperand(Opcode operand) {
        this.operand = operand;
    }

    public Arg getArg1() {
        return arg1;
    }

    public void setArg1(Arg arg1) {
        this.arg1 = arg1;
    }

    public Arg getArg2() {
        return arg2;
    }

    public void setArg2(Arg arg2) {
        this.arg2 = arg2;
    }

    public Arg getResult() {
        return result;
    }

    public void setResult(Arg result) {
        this.result = result;
    }

    public boolean isConditional() {
        switch(operand) {
            case JUMP:
            case COND:
            case IFT:
            case IFF:
//            case CALL:
//            case EXIT:
                return true;
        }
        return false;
    }


    public List<Integer> getConditionalTargets() {
        List<Integer> insts = new ArrayList<>();
        switch(operand) {
            case JUMP:
                assert arg1 != null : "Invalid generated opcode";
                insts.add(((ArgLabel)arg1).address);
                break;
            case COND:
                insts.add(((ArgLabel)arg2).address);
                insts.add(((ArgLabel)result).address);
                break;
            case IFT:
                insts.add(((ArgLabel)arg2).address);
                break;
            case IFF:
                insts.add(((ArgLabel)arg2).address);
                break;
            default:
                assert false : "Not supported conditional type " + operand;
        }
        return insts;
    }

    @Override
    public String toString() {
        String s = String
                .format("%1$-10s", operand.name());
        if (arg1 != null) {
            s += arg1;
        }
        if (arg2 != null) {
            s += ", " + arg2;
        }
        if (result != null) {
            s += ", " + result;
        }
        return s;
    }
}
