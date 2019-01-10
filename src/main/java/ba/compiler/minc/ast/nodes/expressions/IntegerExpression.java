package ba.compiler.minc.ast.nodes.expressions;

public class IntegerExpression extends Expression {
    private int value;

    public IntegerExpression(int line, int value) {
        super(line);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public static final class Builder {
        private int line;
        private int value;

        private Builder() {
        }

        public static Builder anIntegerExpression() {
            return new Builder();
        }

        public Builder withLine(int line) {
            this.line = line;
            return this;
        }

        public Builder withValue(int value) {
            this.value = value;
            return this;
        }

        public IntegerExpression build() {
            return new IntegerExpression(line, value);
        }
    }
}
