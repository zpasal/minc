package ba.compiler.minc.ast.nodes;

public class AstNode {
    private int line;

    public AstNode(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

}
