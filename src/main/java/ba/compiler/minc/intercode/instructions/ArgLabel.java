package ba.compiler.minc.intercode.instructions;

public class ArgLabel extends Arg {
    public int address;

    @Override
    public String toString() {
        return "A(" + address + ")";
    }
}
