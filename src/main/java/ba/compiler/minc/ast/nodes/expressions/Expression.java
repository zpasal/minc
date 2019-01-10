package ba.compiler.minc.ast.nodes.expressions;

import ba.compiler.minc.ast.nodes.AstNode;
import ba.compiler.minc.intercode.instructions.Arg;

public abstract class Expression extends AstNode {
    // Synthesised attributes
    private Arg address;
    private int type;
    private int width;

    public Expression(int line) {
        super(line);
    }

    public Arg getAddress() {
        return address;
    }

    public void setAddress(Arg address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
