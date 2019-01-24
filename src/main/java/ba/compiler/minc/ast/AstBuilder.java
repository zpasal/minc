package ba.compiler.minc.ast;

import ba.compiler.minc.ast.nodes.Block;
import ba.compiler.minc.ast.nodes.CompilationUnit;
import ba.compiler.minc.ast.nodes.declarations.Declaration;
import ba.compiler.minc.ast.nodes.declarations.FuncDeclaration;
import ba.compiler.minc.ast.nodes.declarations.Type;
import ba.compiler.minc.ast.nodes.declarations.VarDeclaration;
import ba.compiler.minc.ast.nodes.expressions.*;
import ba.compiler.minc.ast.nodes.statements.*;
import ba.compiler.minc.parser.MinCBaseVisitor;
import ba.compiler.minc.parser.MinCLexer;
import ba.compiler.minc.parser.MinCParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AstBuilder {

    public CompilationUnit parse(CharStream charStream) {
        MinCLexer lexer = new MinCLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        MinCParser parser = new MinCParser(tokens);

        CompilationUnitVisitor compilationUnitVisitor = new CompilationUnitVisitor();
        return compilationUnitVisitor.visit(parser.compilationUnit());
    }

    private static class CompilationUnitVisitor extends MinCBaseVisitor<CompilationUnit> {
        @Override
        public CompilationUnit visitCompilationUnit(MinCParser.CompilationUnitContext ctx) {
            VarDeclarationVisitor varDeclarationVisitor = new VarDeclarationVisitor();
            FuncDeclarationVisitor funcDeclarationVisitor = new FuncDeclarationVisitor();

            List<Declaration> declarations = ctx.declarationItem().stream()
                    .map(x -> x.varDeclaration() != null ?
                                x.varDeclaration().accept(varDeclarationVisitor) :
                                x.funcDeclaration().accept(funcDeclarationVisitor)
                    )
                    .collect(Collectors.toList());

            return CompilationUnit.Builder.aCompilationUnit()
                    .withLine(ctx.start.getLine())
                    .withDeclarations(declarations)
                    .build();
        }
    }

    private static class VarDeclarationVisitor extends MinCBaseVisitor<VarDeclaration> {
        @Override
        public VarDeclaration visitVarDeclaration(MinCParser.VarDeclarationContext ctx) {
            VarInnerDeclarationVisitor varInnerDeclarationVisitor = new VarInnerDeclarationVisitor();
            return ctx.varInnerDeclaration().accept(varInnerDeclarationVisitor);
        }
    }

    private static class VarInnerDeclarationVisitor extends MinCBaseVisitor<VarDeclaration> {
        @Override
        public VarDeclaration visitVarInnerDeclaration(MinCParser.VarInnerDeclarationContext ctx) {
            TypeVisitor typeVisitor = new TypeVisitor();

            Type type = ctx.type().accept(typeVisitor);

            return VarDeclaration.Builder.aVarDeclaration()
                    .withLine(ctx.start.getLine())
                    .withName(ctx.Identifier().getText())
                    .withType(type.getType())
                    .withDimensions(type.getDimensions())
                    .build();
        }
    }

    private static class TypeVisitor extends MinCBaseVisitor<Type> {
        @Override
        public Type visitType(MinCParser.TypeContext ctx) {
            List<Integer> dimensions = new ArrayList<>();
            if (ctx.dimensions() != null) {
                dimensions = ctx.dimensions().IntegerLiteral().stream()
                        .map(x -> Integer.valueOf(x.getText()))
                        .collect(Collectors.toList());
            }

            return Type.Builder.aType()
                    .withType(ctx.baseType.getType())
                    .withDimensions(dimensions)
                    .build();
        }
    }

    private static class FuncDeclarationVisitor extends MinCBaseVisitor<FuncDeclaration> {
        @Override
        public FuncDeclaration visitFuncDeclaration(MinCParser.FuncDeclarationContext ctx) {
            VarInnerDeclarationVisitor varInnerDeclarationVisitor = new VarInnerDeclarationVisitor();
            TypeVisitor typeVisitor = new TypeVisitor();
            FunctionBlockVisitor functionBlockVisitor = new FunctionBlockVisitor();

            // Function Params
            List<VarDeclaration> params = new ArrayList<>();
            if (ctx.paramDefinitions() != null) {
                params = ctx.paramDefinitions().varInnerDeclaration().stream()
                        .map(x -> x.accept(varInnerDeclarationVisitor))
                        .collect(Collectors.toList());
            }

            // Function Type
            Type returnType = ctx.type().accept(typeVisitor);

            // Function Block
            Block block = ctx.functionBlock().accept(functionBlockVisitor);

            return FuncDeclaration.Builder.aFuncDeclaration()
                    .withLine(ctx.start.getLine())
                    .withName(ctx.Identifier().getText())
                    .withParams(params)
                    .withReturnType(returnType.getType())
                    .withReturnTypeDimensions(returnType.getDimensions())
                    .withBlock(block)
                    .build();
        }
    }

    private static class FunctionBlockVisitor extends MinCBaseVisitor<Block> {
        @Override
        public Block visitFunctionBlock(MinCParser.FunctionBlockContext ctx) {
            AssignmentStatementVisitor assignmentStatementVisitor = new AssignmentStatementVisitor();
            ReturnStatementVisitor returnStatementVisitor = new ReturnStatementVisitor();
            VarDeclarationVisitor varDeclarationVisitor = new VarDeclarationVisitor();
            IfStatementVisitor ifStatementVisitor = new IfStatementVisitor();
            WhileStatementVisitor whileStatementVisitor = new WhileStatementVisitor();

            List<Declaration> declarations = ctx.varDeclaration().stream()
                    .map(var -> var.accept(varDeclarationVisitor))
                    .collect(Collectors.toList());
            List<Statement> statements = ctx.statement().stream()
                    .map(statement -> {
                        if (statement.assignmentStatement() != null) {
                            return statement.accept(assignmentStatementVisitor);
                        }
                        else if (statement.returnStatement() != null) {
                            return statement.accept(returnStatementVisitor);
                        }
                        else if (statement.ifStatement() != null) {
                            return statement.accept(ifStatementVisitor);
                        }
                        else if (statement.whileStatement() != null) {
                            return statement.accept(whileStatementVisitor);
                        }
                        return null;
                    })
                    .collect(Collectors.toList());

            return Block.Builder.aBlock()
                    .withLine(ctx.start.getLine())
                    .withDeclarations(declarations)
                    .withStatements(statements)
                    .build();
        }
    }

    private static class BlockVisitor extends MinCBaseVisitor<Block> {
        @Override
        public Block visitBlock(MinCParser.BlockContext ctx) {
            AssignmentStatementVisitor assignmentStatementVisitor = new AssignmentStatementVisitor();
            ReturnStatementVisitor returnStatementVisitor = new ReturnStatementVisitor();
            IfStatementVisitor ifStatementVisitor = new IfStatementVisitor();
            WhileStatementVisitor whileStatementVisitor = new WhileStatementVisitor();

            List<Statement> statements = ctx.statement().stream()
                    .map(statement -> {
                        if (statement.assignmentStatement() != null) {
                            return statement.accept(assignmentStatementVisitor);
                        }
                        else if (statement.returnStatement() != null) {
                            return statement.accept(returnStatementVisitor);
                        }
                        else if (statement.ifStatement() != null) {
                            return statement.accept(ifStatementVisitor);
                        }
                        else if (statement.whileStatement() != null) {
                            return statement.accept(whileStatementVisitor);
                        }
                        return null;
                    })
                    .collect(Collectors.toList());

            return Block.Builder.aBlock()
                    .withLine(ctx.start.getLine())
                    .withStatements(statements)
                    .withDeclarations(new ArrayList<>())
                    .build();
        }
    }

    private static class ReturnStatementVisitor extends MinCBaseVisitor<ReturnStatement> {
        @Override
        public ReturnStatement visitReturnStatement(MinCParser.ReturnStatementContext ctx) {
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();

            return ReturnStatement.Builder.aReturnStatement()
                    .withLine(ctx.start.getLine())
                    .withExpression(ctx.expression().accept(expressionVisitor))
                    .build();
        }
    }

    private static class AssignmentStatementVisitor extends MinCBaseVisitor<AssignmentStatement> {
        @Override
        public AssignmentStatement visitAssignmentStatement(MinCParser.AssignmentStatementContext ctx) {
            LValueStatementVisitor lValueStatementVisitor = new LValueStatementVisitor();
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();

            LValue lValue = ctx.lValue().accept(lValueStatementVisitor);
            Expression expression = ctx.expression().accept(expressionVisitor);

            return AssignmentStatement.Builder.anAssignmentStatement()
                    .withLine(ctx.start.getLine())
                    .withLValue(lValue)
                    .withExpression(expression)
                    .build();
        }
    }

    private static class IfStatementVisitor extends MinCBaseVisitor<IfStatement> {
        @Override
        public IfStatement visitIfStatement(MinCParser.IfStatementContext ctx) {
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();
            BlockVisitor blockVisitor = new BlockVisitor();

            Expression expression = ctx.expression().accept(expressionVisitor);
            Block block = ctx.block().accept(blockVisitor);

            return IfStatement.Builder.anIfStatement()
                    .withLine(ctx.start.getLine())
                    .withExpression(expression)
                    .withBlock(block)
                    .build();
        }
    }

    private static class WhileStatementVisitor extends MinCBaseVisitor<WhileStatement> {
        @Override
        public WhileStatement visitWhileStatement(MinCParser.WhileStatementContext ctx) {
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();
            BlockVisitor blockVisitor = new BlockVisitor();

            Expression expression = ctx.expression().accept(expressionVisitor);
            Block block = ctx.block().accept(blockVisitor);

            return WhileStatement.Builder.aWhileStatement()
                    .withLine(ctx.start.getLine())
                    .withExpression(expression)
                    .withBlock(block)
                    .build();
        }
    }

    private static class LValueStatementVisitor extends MinCBaseVisitor<LValue> {
        @Override
        public LValue visitLValue(MinCParser.LValueContext ctx) {
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();

            List<Expression> indexes = new ArrayList<>();
            if (ctx.indexes() != null) {
                indexes = ctx.indexes().expression().stream()
                        .map(expression -> expression.accept(expressionVisitor))
                        .collect(Collectors.toList());
            }
            return LValue.Builder.aLValue()
                    .withName(ctx.Identifier().getText())
                    .withIndexes(indexes)
                    .build();
        }
    }

    private static class ExpressionVisitor extends MinCBaseVisitor<Expression> {
        @Override
        public Expression visitUnaryMinusExpression(MinCParser.UnaryMinusExpressionContext ctx) {
            return UnaryExpression.Builder.anUnaryExpression()
                    .withLine(ctx.start.getLine())
                    .withOperator(MinCLexer.Minus)
                    .withExpression(this.visit(ctx.expression()))
                    .build();
        }

        @Override
        public Expression visitNotExpression(MinCParser.NotExpressionContext ctx) {
            return UnaryExpression.Builder.anUnaryExpression()
                    .withLine(ctx.start.getLine())
                    .withOperator(MinCLexer.Not)
                    .withExpression(this.visit(ctx.expression()))
                    .build();
        }

        @Override
        public Expression visitMultExpression(MinCParser.MultExpressionContext ctx) {
            return visitBinary(ctx.expression(0), ctx.expression(1), ctx.op.getType(), ctx.start.getLine());
        }

        @Override
        public Expression visitAddExpression(MinCParser.AddExpressionContext ctx) {
            return visitBinary(ctx.expression(0), ctx.expression(1), ctx.op.getType(), ctx.start.getLine());
        }

        @Override
        public Expression visitCompExpression(MinCParser.CompExpressionContext ctx) {
            return visitBinary(ctx.expression(0), ctx.expression(1), ctx.op.getType(), ctx.start.getLine());
        }

        @Override
        public Expression visitEqExpression(MinCParser.EqExpressionContext ctx) {
            return visitBinary(ctx.expression(0), ctx.expression(1), ctx.op.getType(), ctx.start.getLine());
        }

        @Override
        public Expression visitAndExpression(MinCParser.AndExpressionContext ctx) {
            return visitBinary(ctx.expression(0), ctx.expression(1), MinCLexer.And, ctx.start.getLine());
        }

        @Override
        public Expression visitOrExpression(MinCParser.OrExpressionContext ctx) {
            return visitBinary(ctx.expression(0), ctx.expression(1), MinCLexer.Or, ctx.start.getLine());
        }

        @Override
        public Expression visitIntegerExpression(MinCParser.IntegerExpressionContext ctx) {
            return IntegerExpression.Builder.anIntegerExpression()
                    .withLine(ctx.start.getLine())
                    .withValue(Integer.valueOf(ctx.getText()))
                    .build();
        }

        @Override
        public Expression visitRealExpression(MinCParser.RealExpressionContext ctx) {
            return RealExpression.Builder.aRealExpression()
                    .withLine(ctx.start.getLine())
                    .withValue(Double.valueOf(ctx.getText()))
                    .build();
        }

        @Override
        public Expression visitBoolExpression(MinCParser.BoolExpressionContext ctx) {
            return BoolExpression.Builder.aBoolExpression()
                    .withLine(ctx.start.getLine())
                    .withValue(Boolean.valueOf(ctx.getText()))
                    .build();
        }

        @Override
        public Expression visitIdentifierExpression(MinCParser.IdentifierExpressionContext ctx) {
            List<Expression> indexes = new ArrayList<>();
            if (ctx.indexes() != null) {
                indexes = ctx.indexes().expression().stream()
                        .map(this::visit)
                        .collect(Collectors.toList());
            }

            return IdentifierExpression.Builder.anIdentifierExpression()
                    .withLine(ctx.start.getLine())
                    .withName(ctx.Identifier().getText())
                    .withIndexes(indexes)
                    .build();
        }

        @Override
        public Expression visitExpressionExpression(MinCParser.ExpressionExpressionContext ctx) {
            return visit(ctx.expression());
        }

        @Override
        public Expression visitFunctionCallExpression(MinCParser.FunctionCallExpressionContext ctx) {
            List<Expression> args = ctx.functionCall().exprList().expression().stream()
                    .map(this::visit)
                    .collect(Collectors.toList());

            List<Expression> indexes = new ArrayList<>();
            if (ctx.indexes() != null) {
                indexes = ctx.indexes().expression().stream()
                        .map(this::visit)
                        .collect(Collectors.toList());
            }

            return FunctionCallExpression.Builder.aFunctionCallExpression()
                    .withLine(ctx.start.getLine())
                    .withName(ctx.functionCall().Identifier().getText())
                    .withArguments(args)
                    .withReturnIndexes(indexes)
                    .build();
        }

        private Expression visitBinary(MinCParser.ExpressionContext l, MinCParser.ExpressionContext r, int op, int line) {
            Expression left = this.visit(l);
            Expression right = this.visit(r);
            return BinaryExpression.Builder.aBinaryExpression()
                    .withLine(line)
                    .withOperator(op)
                    .withLeft(left)
                    .withRight(right)
                    .build();
        }
    }

}
