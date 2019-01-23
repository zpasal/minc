package ba.compiler.minc.ast.nodes.statements;

import ba.compiler.minc.ast.nodes.Block;
import ba.compiler.minc.ast.nodes.expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class WhileStatement extends Statement {
    private Expression expression;
    private Block block;

    public WhileStatement(int line, Expression expression, Block block) {
        super(line);
        this.expression = expression;
        this.block = block;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }


    public static final class Builder {
        private int line;
        private Expression expression;
        private List<Integer> nextList = new ArrayList<>();
        private Block block;

        private Builder() {
        }

        public static Builder aWhileStatement() {
            return new Builder();
        }

        public Builder withLine(int line) {
            this.line = line;
            return this;
        }

        public Builder withExpression(Expression expression) {
            this.expression = expression;
            return this;
        }

        public Builder withNextList(List<Integer> nextList) {
            this.nextList = nextList;
            return this;
        }

        public Builder withBlock(Block block) {
            this.block = block;
            return this;
        }

        public WhileStatement build() {
            WhileStatement whileStatement = new WhileStatement(line, expression, block);
            whileStatement.setNextList(nextList);
            return whileStatement;
        }
    }
}
