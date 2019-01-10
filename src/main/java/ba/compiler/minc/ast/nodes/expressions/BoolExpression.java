package ba.compiler.minc.ast.nodes.expressions;

public class BoolExpression extends Expression {
    private boolean value;

    public BoolExpression(int line, boolean value) {
        super(line);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }


    public static final class Builder {
        public boolean value;
        private int line;

        private Builder() {
        }

        public static Builder aBoolExpression() {
            return new Builder();
        }

        public Builder withLine(int line) {
            this.line = line;
            return this;
        }

        public Builder withValue(boolean value) {
            this.value = value;
            return this;
        }

        public BoolExpression build() {
            return new BoolExpression(line, value);
        }
    }
}
