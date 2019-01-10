package ba.compiler.minc.ast.nodes.statements;

import ba.compiler.minc.ast.nodes.AstNode;

public abstract class Statement extends AstNode {
    public Statement(int line) {
        super(line);
    }
}
