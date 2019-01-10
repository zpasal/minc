package ba.compiler.minc.intercode.instructions;

public class ArgReal extends Arg {
    public double value;

    public ArgReal(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
