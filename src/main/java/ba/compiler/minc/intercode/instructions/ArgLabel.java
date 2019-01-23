package ba.compiler.minc.intercode.instructions;

public class ArgLabel extends Arg {
    public int address;

    public ArgLabel(int address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "@" + address ;
    }
}
