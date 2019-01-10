package ba.compiler.minc.intercode.instructions;

import ba.compiler.minc.ast.nodes.declarations.VarDeclaration;

public class ArgIdentifier extends Arg {
    public String name;

    public ArgIdentifier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "(" + name + ")";
    }
}
