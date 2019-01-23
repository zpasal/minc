package ba.compiler.minc.ast.nodes.statements;

import ba.compiler.minc.ast.nodes.Block;
import ba.compiler.minc.ast.nodes.expressions.Expression;
import ba.compiler.minc.intercode.instructions.ArgLabel;

public class IfStatement extends Statement {
    private Expression expression;
    private Block block;


    public IfStatement(int line, Expression expression, Block block) {
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
        private Block block;

        private Builder() {
        }

        public static Builder anIfStatement() {
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

        public Builder withBlock(Block block) {
            this.block = block;
            return this;
        }

        public IfStatement build() {
            IfStatement ifStatement = new IfStatement(line, expression, block);
            return ifStatement;
        }
    }
}
