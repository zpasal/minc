package ba.compiler.minc.ast.nodes.declarations;

import ba.compiler.minc.ast.nodes.AstNode;

public abstract class Declaration extends AstNode {
    public Declaration(int line) {
        super(line);
    }
}
