package ba.compiler.minc.intercode;

import ba.compiler.minc.ast.AstVisitor;
import ba.compiler.minc.ast.nodes.Block;
import ba.compiler.minc.ast.nodes.CompilationUnit;
import ba.compiler.minc.ast.nodes.declarations.FuncDeclaration;
import ba.compiler.minc.ast.nodes.declarations.VarDeclaration;
import ba.compiler.minc.ast.nodes.expressions.*;
import ba.compiler.minc.ast.nodes.statements.AssignmentStatement;
import ba.compiler.minc.ast.nodes.statements.IfStatement;
import ba.compiler.minc.ast.nodes.statements.WhileStatement;
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

        // todo = return value
        ic.gen(Opcode.EXIT, null, null, null);
        currentEnv = saved;
    }

    @Override
    public void visit(AssignmentStatement node) {
        super.visit(node);

        VarDeclaration varDeclaration = currentEnv.get(node.getlValue().getName());

        int varDimensions = varDeclaration.getDimensions().size();
        if (varDimensions != node.getlValue().getIndexes().size()) {
            throw new RuntimeException("Expected " + varDimensions + " dimensions");
        }

        // Calc index expressions
        node.getlValue().getIndexes().stream().forEach(x -> visit(x));

        // Assign - for now support only 1d and 2d arrays - todo - generalise
        ArgIdentifier result = new ArgIdentifier(node.getlValue().getName());
        switch(varDimensions) {
            case 0:
                Arg newTypeArg = ic.widen(
                        node.getExpression().getAddress(),
                        node.getExpression().getType(),
                        varDeclaration.getType()
                );
                ic.gen(Opcode.CPY, newTypeArg, null, result);
                break;
            case 1:
                if (node.getlValue().getIndexes().get(0).getType() != MinCLexer.IntType) {
                    throw new RuntimeException("Index must be integer");
                }
                newTypeArg = ic.widen(
                        node.getExpression().getAddress(),
                        node.getExpression().getType(),
                        varDeclaration.getType()
                );
                ic.gen(Opcode.IDXASG,
                        result,
                        node.getlValue().getIndexes().get(0).getAddress(),
                        newTypeArg) ;
                break;

            case 2:
                if (node.getlValue().getIndexes().get(0).getType() != MinCLexer.IntType) {
                    throw new RuntimeException("First index must be integer");
                }
                if (node.getlValue().getIndexes().get(1).getType() != MinCLexer.IntType) {
                    throw new RuntimeException("Second index must be integer");
                }

                // a[i][j] = a[j + i * Wi] = a[index[1] + index[0]*Wi]
                ArgTemp argIMulWi = ic.newTemp();
                ArgTemp argJIMulWi = ic.newTemp();
                // i * Wi
                ic.gen(Opcode.MUL,
                        node.getlValue().getIndexes().get(0).getAddress(),      // i  - a[i][j]
                        new ArgInteger(varDeclaration.getDimensions().get(0)),  // Wi - width of matrix
                        argIMulWi);                                             // == i * Wi
                ic.gen(Opcode.ADD,
                        node.getlValue().getIndexes().get(1).getAddress(),                  // j - a[i][j]
                        argIMulWi,                                              // i * Hi
                        argJIMulWi);                                            // == j + i*Hi

                newTypeArg = ic.widen(
                        node.getExpression().getAddress(),
                        node.getExpression().getType(),
                        varDeclaration.getType()
                );
                ic.gen(Opcode.IDXASG,
                        result,
                        argJIMulWi,
                        newTypeArg) ;
                break;
        }


    }

    @Override
    public void visit(UnaryExpression node) {
        super.visit(node.getExpression());

        switch(node.getOperator()) {
            case MinCLexer.Minus:
                Arg arg1 = node.getExpression().getAddress();
                ArgTemp res = ic.newTemp();
                node.setAddress(res);
                ic.gen(Opcode.NEG, arg1, null, res);

                node.setType(node.getExpression().getType());
                node.setWidth(node.getExpression().getWidth());
                break;
            case MinCLexer.Not:
                arg1 = node.getExpression().getAddress();
                res = ic.newTemp();
                node.setAddress(res);
                ic.gen(Opcode.NOT, arg1, null, res);
                node.setType(MinCLexer.BoolType);
                node.setWidth(1);
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
        else {
            throw new RuntimeException("Unsupported binary operator : " + op);
        }
    }

    private void visitBinaryRel(BinaryExpression node) {
        visit(node.getLeft());
        visit(node.getRight());

        int op = node.getOperator();
        ArgTemp res = ic.newTemp();
        node.setAddress(res);
        node.setType(MinCLexer.BoolType);
        node.setWidth(1);

        switch (op) {
            case MinCLexer.Gt:
                ic.gen(Opcode.ISGT, node.getLeft().getAddress(), node.getRight().getAddress(), res);
                break;
            case MinCLexer.GtE:
                ic.gen(Opcode.ISGTE, node.getLeft().getAddress(), node.getRight().getAddress(), res);
                break;
            case MinCLexer.Lt:
                ic.gen(Opcode.ISLT, node.getLeft().getAddress(), node.getRight().getAddress(), res);
                break;
            case MinCLexer.LtE:
                ic.gen(Opcode.ISLTE, node.getLeft().getAddress(), node.getRight().getAddress(), res);
                break;
            case MinCLexer.Equ:
                ic.gen(Opcode.ISEQ, node.getLeft().getAddress(), node.getRight().getAddress(), res);
                break;
            case MinCLexer.Nequ:
                ic.gen(Opcode.ISNEQU, node.getLeft().getAddress(), node.getRight().getAddress(), res);
                break;
            case MinCLexer.Or:
                ic.gen(Opcode.LOR, node.getLeft().getAddress(), node.getRight().getAddress(), res);
                break;
            case MinCLexer.And:
                ic.gen(Opcode.LAND, node.getLeft().getAddress(), node.getRight().getAddress(), res);
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
    }

    @Override
    public void visit(IfStatement node) {
        visit(node.getExpression());

        ArgLabel trueLabel = new ArgLabel(ic.getNextInstruction()+1);
        ic.gen(Opcode.COND, node.getExpression().getAddress(), trueLabel, null);
        int instToPatch = ic.getNextInstruction() - 1;

        visit(node.getBlock());

        // Back-patch false label
        int label = ic.getNextInstruction();
        ArgLabel falseLabel = new ArgLabel(label);
        ic.getInstructions().get(instToPatch).setResult(falseLabel);
    }

    @Override
    public void visit(WhileStatement node) {

        int instEntryPoint = ic.getNextInstruction();

        visit(node.getExpression());

        ArgLabel trueLabel = new ArgLabel(ic.getNextInstruction()+1);
        ic.gen(Opcode.COND, node.getExpression().getAddress(), trueLabel, null);
        int instToPatch = ic.getNextInstruction() - 1;

        visit(node.getBlock());

        // jumo back to while loop
        ic.gen(Opcode.JUMP, new ArgLabel(instEntryPoint), null, null);

        // Back-patch false label
        int label = ic.getNextInstruction();
        ArgLabel falseLabel = new ArgLabel(label);
        ic.getInstructions().get(instToPatch).setResult(falseLabel);

    }

    @Override
    public void visit(IdentifierExpression node) {
        VarDeclaration varDeclaration = currentEnv.get(node.getName());
        if (varDeclaration == null) {
            throw new RuntimeException("Undefined var " + node.getName());
        }

        int varDimensions = varDeclaration.getDimensions().size();
        if (varDimensions != node.getIndexes().size()) {
            throw new RuntimeException("Expected " + varDimensions + " dimensions");
        }

        // Calc index expressions
        node.getIndexes().stream().forEach(x -> visit(x));

        // for now simplify and support only up to 2 dimensions .. todo - generalise this
        ArgTemp res = ic.newTemp();
        switch(varDimensions) {
            case 0:
                ic.gen(Opcode.LOAD, new ArgIdentifier(node.getName()), null, res);
                break;
            case 1:
                if (node.getIndexes().get(0).getType() != MinCLexer.IntType) {
                    throw new RuntimeException("Index must be integer");
                }
                ic.gen(Opcode.LOAD, new ArgIdentifier(node.getName()), node.getIndexes().get(0).getAddress(), res);
                break;
            case 2:
                if (node.getIndexes().get(0).getType() != MinCLexer.IntType) {
                    throw new RuntimeException("First index must be integer");
                }
                if (node.getIndexes().get(1).getType() != MinCLexer.IntType) {
                    throw new RuntimeException("Second index must be integer");
                }
                // a[i][j] = a[j + i * Wi] = a[index[1] + index[0]*Wi]
                ArgTemp argIMulWi = ic.newTemp();
                ArgTemp argJIMulWi = ic.newTemp();
                // i * Wi
                ic.gen(Opcode.MUL,
                        node.getIndexes().get(0).getAddress(),                  // i  - a[i][j]
                        new ArgInteger(varDeclaration.getDimensions().get(0)),  // Wi - width of matrix
                        argIMulWi);                                             // == i * Wi
                ic.gen(Opcode.ADD,
                        node.getIndexes().get(1).getAddress(),                  // j - a[i][j]
                        argIMulWi,                                              // i * Hi
                        argJIMulWi);                                            // == j + i*Hi
                ic.gen(Opcode.IDXCPY, new ArgIdentifier(node.getName()), argJIMulWi, res);
                break;
        }

        node.setAddress(res);
        node.setType(varDeclaration.getType());
        node.setWidth(varDeclaration.getWidth());
    }

    @Override
    public void visit(IntegerExpression node) {
        ArgTemp res = ic.newTemp();
        ic.gen(Opcode.LOAD, new ArgInteger(node.getValue()), null, res);

        node.setAddress(res);
        node.setType(MinCLexer.IntType);
        node.setWidth(4);
    }

    @Override
    public void visit(RealExpression node) {
        ArgTemp res = ic.newTemp();
        ic.gen(Opcode.LOAD, new ArgReal(node.getValue()), null, res);

        node.setAddress(res);
        node.setType(MinCLexer.RealType);
        node.setWidth(8);
    }

    @Override
    public void visit(BoolExpression node) {
        ArgTemp res = ic.newTemp();
        ic.gen(Opcode.LOAD, new ArgBoolean(node.getValue()), null, res);

        node.setAddress(res);
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
                op == MinCLexer.Nequ ||
                op == MinCLexer.Or ||
                op == MinCLexer.And;
    }

    private boolean isArithmeticOperator(int op) {
        return op == MinCLexer.Minus ||
                op == MinCLexer.Plus ||
                op == MinCLexer.Mul ||
                op == MinCLexer.Div ||
                op == MinCLexer.Mod;
    }

}
