package ba.compiler.minc.ast.nodes.expressions;

public class BinaryExpression extends Expression {
    private int operator;
    private Expression left;
    private Expression right;

    public BinaryExpression(int line, int operator, Expression left, Expression right) {
        super(line);
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }


    public static final class Builder {
        private int line;
        private int operator;
        private Expression left;
        private Expression right;

        private Builder() {
        }

        public static Builder aBinaryExpression() {
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

        public Builder withLeft(Expression left) {
            this.left = left;
            return this;
        }

        public Builder withRight(Expression right) {
            this.right = right;
            return this;
        }

        public BinaryExpression build() {
            return new BinaryExpression(line, operator, left, right);
        }
    }
}
