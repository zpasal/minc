package ba.compiler.minc.intercode.instructions;

public class ArgBoolean extends Arg {
    public boolean value;

    public ArgBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
