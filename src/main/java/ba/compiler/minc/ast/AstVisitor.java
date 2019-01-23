package ba.compiler.minc.ast;

import ba.compiler.minc.ast.nodes.AstNode;
import ba.compiler.minc.ast.nodes.Block;
import ba.compiler.minc.ast.nodes.CompilationUnit;
import ba.compiler.minc.ast.nodes.declarations.Declaration;
import ba.compiler.minc.ast.nodes.declarations.FuncDeclaration;
import ba.compiler.minc.ast.nodes.declarations.VarDeclaration;
import ba.compiler.minc.ast.nodes.expressions.*;
import ba.compiler.minc.ast.nodes.statements.*;

public class AstVisitor {

    public void visit(CompilationUnit node) {
        node.getDeclarations().stream().forEach(x -> visit(x));
    }

    public void visit(Declaration node) {
        if (node instanceof VarDeclaration) {
            visit((VarDeclaration)node);
        }
        else if (node instanceof FuncDeclaration) {
            visit((FuncDeclaration)node);
        }
    }

    public void visit(VarDeclaration node) {
    }

    public void visit(FuncDeclaration node) {
        node.getParams().stream().forEach(x -> visit(x));
        visit(node.getBlock());
    }

    public void visit(Block node) {
        node.getDeclarations().stream().forEach(x -> visit(x));
        node.getStatements().stream().forEach(x -> visit(x));
    }

    public void visit(Statement node) {
        if (node instanceof AssignmentStatement) {
            visit((AssignmentStatement)node);
        }
        else if (node instanceof ReturnStatement) {
            visit((ReturnStatement)node);
        }
        else if (node instanceof IfStatement) {
            visit((IfStatement)node);
        }
        else if (node instanceof WhileStatement) {
            visit((WhileStatement)node);
        }
    }

    public void visit(IfStatement node) {
        visit(node.getExpression());
        visit(node.getBlock());
    }

    public void visit(WhileStatement node) {
        visit(node.getExpression());
        visit(node.getBlock());
    }

    public void visit(AssignmentStatement node) {
        visit(node.getExpression());
    }

    public void visit(ReturnStatement node) {
        visit(node.getExpression());
    }

    public void visit(Expression node) {
        if (node instanceof BinaryExpression) {
            visit((BinaryExpression)node);
        }
        else if (node instanceof BoolExpression) {
            visit((BoolExpression) node);
        }
        else if (node instanceof FunctionCallExpression) {
            visit((FunctionCallExpression)node);
        }
        else if (node instanceof IdentifierExpression) {
            visit((IdentifierExpression)node);
        }
        else if (node instanceof IntegerExpression) {
            visit((IntegerExpression) node);
        }
        else if (node instanceof UnaryExpression) {
            visit((UnaryExpression)node);
        }
        else if (node instanceof RealExpression) {
            visit((RealExpression)node);
        }
        else {
            throw new RuntimeException("Unsupported node " + node);
        }
    }

    public void visit(BinaryExpression node) {
        visit(node.getLeft());
        visit(node.getRight());
    }

    public void visit(BoolExpression node) {
    }

    public void visit(FunctionCallExpression node) {
        node.getArguments().stream().forEach(x -> visit(x));
        node.getReturnIndexes().stream().forEach(x -> visit(x));
    }

    public void visit(IdentifierExpression node) {
        node.getIndexes().stream().forEach(x -> visit(x));
    }

    public void visit(IntegerExpression node) {}

    public void visit(RealExpression node) {}

    public void visit(UnaryExpression node) {
        visit(node.getExpression());
    }

}
