package ba.compiler.minc.intercode;

import ba.compiler.minc.ast.AstVisitor;
import ba.compiler.minc.ast.nodes.Block;
import ba.compiler.minc.ast.nodes.CompilationUnit;
import ba.compiler.minc.ast.nodes.declarations.FuncDeclaration;
import ba.compiler.minc.ast.nodes.declarations.VarDeclaration;
import ba.compiler.minc.ast.nodes.expressions.*;
import ba.compiler.minc.ast.nodes.statements.AssignmentStatement;
import ba.compiler.minc.ast.nodes.statements.IfStatement;
import ba.compiler.minc.idents.Env;
import ba.compiler.minc.idents.Types;
import ba.compiler.minc.intercode.instructions.*;
import ba.compiler.minc.parser.MinCLexer;

import java.util.ArrayList;
import java.util.List;

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

        switch(node.getOperator()) {
            case MinCLexer.Minus:
                Arg arg1 = node.getExpression().getAddress();
                ArgTemp result = ic.newTemp();
                node.setAddress(result);
                ic.gen(Opcode.NEG, arg1, null, result);
                break;
            case MinCLexer.Not:
                break;
            default:
                throw new RuntimeException("Unsupported operator: " + node.getOperator());
        }
    }

    @Override
    public void visit(BinaryExpression node) {
        int op = node.getOperator();
        if (isArithmeticOperator(op)) {
            visitBinaryArithmetic(node);
        }
        else if (isRelOperator(op)) {
            visitBinaryRel(node);
        }
        else if (isLogicOperator(op)) {
            visitBinaryLogic(node);
        }
        else {
            throw new RuntimeException("Unsupported binary operator : " + op);
        }
    }

    private void visitBinaryLogic(BinaryExpression node) {
        visit(node.getLeft());
        int labelToRight = ic.getNextInstruction();
        visit(node.getRight());

        node.setType(MinCLexer.BoolType);
        node.setWidth(1);

        int op = node.getOperator();
        switch (op) {
            case MinCLexer.And:
                break;
            case MinCLexer.Or:
                break;
            default:
                throw new RuntimeException("Unsupported binary logic operator : " + op);
        }

    }

    private void visitBinaryRel(BinaryExpression node) {
        visit(node.getLeft());
        visit(node.getRight());

        int op = node.getOperator();

        node.setType(MinCLexer.BoolType);
        node.setWidth(1);

        switch (op) {
            case MinCLexer.Gt:
                ic.gen(Opcode.IFGT, node.getLeft().getAddress(), node.getRight().getAddress(), null);
                ic.gen(Opcode.JUMP, null, null, null);
                break;
            case MinCLexer.GtE:
                ic.gen(Opcode.IFGTE, node.getLeft().getAddress(), node.getRight().getAddress(), null);
                ic.gen(Opcode.JUMP, null, null, null);
                break;
            case MinCLexer.Lt:
                ic.gen(Opcode.IFLT, node.getLeft().getAddress(), node.getRight().getAddress(), null);
                ic.gen(Opcode.JUMP, null, null, null);
                break;
            case MinCLexer.LtE:
                ic.gen(Opcode.IFLTE, node.getLeft().getAddress(), node.getRight().getAddress(), null);
                ic.gen(Opcode.JUMP, null, null, null);
                break;
            case MinCLexer.Equ:
                ic.gen(Opcode.IFEQ, node.getLeft().getAddress(), node.getRight().getAddress(), null);
                ic.gen(Opcode.JUMP, null, null, null);
                break;
            case MinCLexer.Nequ:
                ic.gen(Opcode.IFNEQU, node.getLeft().getAddress(), node.getRight().getAddress(), null);
                ic.gen(Opcode.JUMP, null, null, null);
                break;
            default:
                throw new RuntimeException("Unsupported binary rel operator : " + op);
        }

    }


    private void visitBinaryArithmetic(BinaryExpression node) {
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

        int op = node.getOperator();
        switch (op) {
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
            default:
                throw new RuntimeException("Unsupported binary arithmetic operator : " + op);
        }
    }

    @Override
    public void visit(Block node) {
        super.visit(node);
        node.setNextList(node.getStatements().get(node.getStatements().size()-1).getNextList());
    }

    @Override
    public void visit(IfStatement node) {
        visit(node.getExpression());
        int mInst = ic.getNextInstruction();
        visit(node.getBlock());

        backpatch(node.getExpression().getTrueList(), mInst);

        node.setNextList(merge(
                node.getExpression().getFalseList(),
                node.getBlock().getNextList()
                )
        );
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

    private boolean isRelOperator(int op) {
        return op == MinCLexer.Gt ||
                op == MinCLexer.GtE ||
                op == MinCLexer.Lt ||
                op == MinCLexer.LtE ||
                op == MinCLexer.Equ ||
                op == MinCLexer.Nequ;
    }

    private boolean isLogicOperator(int op) {
        return op == MinCLexer.Or || op == MinCLexer.And;
    }

    private boolean isArithmeticOperator(int op) {
        return op == MinCLexer.Minus ||
                op == MinCLexer.Plus ||
                op == MinCLexer.Mul ||
                op == MinCLexer.Div ||
                op == MinCLexer.Mod;
    }

    private void backpatch(List<Integer> list, int address) {
        list.stream().forEach(x -> {
            ic.getInstructions().get(x).setResult(new ArgLabel(address));
        });
    }

    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new ArrayList<>(list1);
        list.addAll(list2);
        return list;
    }

}
