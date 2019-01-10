package ba.compiler.minc.intercode.instructions;

public class ArgInteger extends Arg {
    public int value;

    public ArgInteger(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
