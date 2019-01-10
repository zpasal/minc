package ba.compiler.minc.intercode;

import ba.compiler.minc.ast.AstVisitor;
import ba.compiler.minc.ast.nodes.CompilationUnit;
import ba.compiler.minc.ast.nodes.declarations.FuncDeclaration;
import ba.compiler.minc.ast.nodes.declarations.VarDeclaration;
import ba.compiler.minc.ast.nodes.expressions.*;
import ba.compiler.minc.ast.nodes.statements.AssignmentStatement;
import ba.compiler.minc.idents.Env;
import ba.compiler.minc.idents.Types;
import ba.compiler.minc.intercode.instructions.*;
import ba.compiler.minc.parser.MinCLexer;

// TODO - index assignment
public class IntermediateCodeGenerator extends AstVisitor {
    private IntermediateCode ic = new IntermediateCode();
    private Env currentEnv;

    @Override
    public void visit(CompilationUnit node) {
        currentEnv = node.getEnv();
        super.visit(node);
    }

    @Override
    public void visit(FuncDeclaration node) {
        Env saved = currentEnv;
        currentEnv = node.getEnv();

        super.visit(node);

        currentEnv = saved;
    }

    @Override
    public void visit(AssignmentStatement node) {
        super.visit(node);

        VarDeclaration varDeclaration = currentEnv.get(node.getlValue().getName());
        ArgIdentifier result = new ArgIdentifier(node.getlValue().getName());

        Arg arg1 = ic.widen(
                node.getExpression().getAddress(),
                node.getExpression().getType(),
                varDeclaration.getType()
        );

        ic.gen(Opcode.CPY, arg1, null, result);
    }

    @Override
    public void visit(UnaryExpression node) {
        super.visit(node.getExpression());

        Arg arg1 = node.getExpression().getAddress();
        ArgTemp result = ic.newTemp();

        node.setAddress(result);

        switch(node.getOperator()) {
            case MinCLexer.Minus:
                ic.gen(Opcode.NEG, arg1, null, result);
                break;
            case MinCLexer.Not:
                throw new RuntimeException("Unsupported unary not!");
            default:
                throw new RuntimeException("Unsupported operator: " + node.getOperator());
        }
    }


    @Override
    public void visit(BinaryExpression node) {
        visit(node.getLeft());
        visit(node.getRight());

        node.setType(Types.max(
                node.getLeft().getType(),
                node.getRight().getType()
        ));

        Arg arg1 = ic.widen(node.getLeft().getAddress(), node.getLeft().getType(), node.getType());
        Arg arg2 = ic.widen(node.getRight().getAddress(), node.getRight().getType(), node.getType());

        ArgTemp result = ic.newTemp();
        node.setAddress(result);

        switch(node.getOperator()) {
            case MinCLexer.Minus:
                ic.gen(Opcode.SUB, arg1, arg2, result);
                break;
            case MinCLexer.Plus:
                ic.gen(Opcode.ADD, arg1, arg2, result);
                break;
            case MinCLexer.Mul:
                ic.gen(Opcode.MUL, arg1, arg2, result);
                break;
            case MinCLexer.Div:
                ic.gen(Opcode.DIV, arg1, arg2, result);
                break;
            case MinCLexer.Mod:
                ic.gen(Opcode.MOD, arg1, arg2, result);
                break;
        }
    }


    @Override
    public void visit(IdentifierExpression node) {
        VarDeclaration varDeclaration = currentEnv.get(node.getName());
        if (varDeclaration == null) {
            throw new RuntimeException("Undefined var " + node.getName());
        }
        node.setAddress(new ArgIdentifier(node.getName()));
        node.setType(varDeclaration.getType());
        node.setWidth(varDeclaration.getWidth());
    }

    @Override
    public void visit(IntegerExpression node) {
        node.setAddress(new ArgInteger(node.getValue()));
        node.setType(MinCLexer.IntType);
        node.setWidth(4);
    }


    @Override
    public void visit(RealExpression node) {
        node.setAddress(new ArgReal(node.getValue()));
        node.setType(MinCLexer.RealType);
        node.setWidth(8);
    }


    @Override
    public void visit(BoolExpression node) {
        node.setAddress(new ArgBoolean(node.getValue()));
        node.setType(MinCLexer.BoolType);
        node.setWidth(1);
    }

    public IntermediateCode getIc() {
        return ic;
    }
}
