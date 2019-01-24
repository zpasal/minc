package ba.compiler.minc.idents;

import ba.compiler.minc.ast.AstVisitor;
import ba.compiler.minc.ast.nodes.CompilationUnit;
import ba.compiler.minc.ast.nodes.declarations.FuncDeclaration;
import ba.compiler.minc.ast.nodes.declarations.VarDeclaration;

public class IdentTableVisitor extends AstVisitor {

    private Env top = new Env(null);

    @Override
    public void visit(CompilationUnit node) {
        node.setEnv(top);
        super.visit(node);
    }

    @Override
    public void visit(VarDeclaration node) {
        if (node.getDimensions().size() > 2) {
            throw new RuntimeException("Only 1d and 2d arrays are supported!");
        }
        top.add(node);
    }

    @Override
    public void visit(FuncDeclaration node) {
        Env saved = top;
        top = new Env(top);
        node.setEnv(top);

        super.visit(node);

        top = saved;
    }

}
