package ba.compiler.minc.ast.nodes.expressions;

public class UnaryExpression extends Expression {
    private int operator;
    private Expression expression;

    public UnaryExpression(int line, int operator, Expression expression) {
        super(line);
        this.operator = operator;
        this.expression = expression;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }


    public static final class Builder {
        private int line;
        private int operator;
        private Expression expression;

        private Builder() {
        }

        public static Builder anUnaryExpression() {
            return new Builder();
        }

        public Builder withLine(int line) {
            this.line = line;
            return this;
        }

        public Builder withOperator(int operator) {
            this.operator = operator;
            return this;
        }

        public Builder withExpression(Expression expression) {
            this.expression = expression;
            return this;
        }

        public UnaryExpression build() {
            return new UnaryExpression(line, operator, expression);
        }
    }
}
