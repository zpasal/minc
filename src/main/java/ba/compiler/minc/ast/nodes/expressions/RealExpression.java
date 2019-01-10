package ba.compiler.minc.ast.nodes.expressions;

import ba.compiler.minc.intercode.instructions.Arg;

public class RealExpression extends Expression {
    private double value;

    public RealExpression(int line, double value) {
        super(line);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public static final class Builder {
        private int line;
        private double value;
        // Synthesised attributes
        private Arg address;
        private int type;
        private int width;

        private Builder() {
        }

        public static Builder aRealExpression() {
            return new Builder();
        }

        public Builder withLine(int line) {
            this.line = line;
            return this;
        }

        public Builder withValue(double value) {
            this.value = value;
            return this;
        }

        public Builder withAddress(Arg address) {
            this.address = address;
            return this;
        }

        public Builder withType(int type) {
            this.type = type;
            return this;
        }

        public Builder withWidth(int width) {
            this.width = width;
            return this;
        }

        public RealExpression build() {
            RealExpression realExpression = new RealExpression(line, value);
            realExpression.setAddress(address);
            realExpression.setType(type);
            realExpression.setWidth(width);
            return realExpression;
        }
    }
}
