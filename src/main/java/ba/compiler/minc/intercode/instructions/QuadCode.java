package ba.compiler.minc.intercode.instructions;

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
