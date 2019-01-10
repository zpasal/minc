package ba.compiler.minc.intercode.instructions;

public class ArgTemp extends Arg {
    public int temp;

    public ArgTemp(int temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "T" + temp;
    }
}
