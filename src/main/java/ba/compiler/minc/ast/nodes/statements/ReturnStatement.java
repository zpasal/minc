package ba.compiler.minc.ast.nodes.statements;

import ba.compiler.minc.ast.nodes.expressions.Expression;

public class ReturnStatement extends Statement {
    public Expression expression;

    public ReturnStatement(int line, Expression expression) {
        super(line);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public static final class Builder {
        public Expression expression;
        private int line;

        private Builder() {
        }

        public static Builder aReturnStatement() {
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

        public ReturnStatement build() {
            return new ReturnStatement(line, expression);
        }
    }
}
