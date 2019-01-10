package ba.compiler.minc.ast.nodes.statements;

import ba.compiler.minc.ast.nodes.expressions.Expression;

public class AssignmentStatement extends Statement {
    private LValue lValue;
    private Expression expression;

    public AssignmentStatement(int line, LValue lValue, Expression expression) {
        super(line);
        this.lValue = lValue;
        this.expression = expression;
    }

    public LValue getlValue() {
        return lValue;
    }

    public void setlValue(LValue lValue) {
        this.lValue = lValue;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public static final class Builder {
        private int line;
        private LValue lValue;
        private Expression expression;

        private Builder() {
        }

        public static Builder anAssignmentStatement() {
            return new Builder();
        }

        public Builder withLine(int line) {
            this.line = line;
            return this;
        }

        public Builder withLValue(LValue lValue) {
            this.lValue = lValue;
            return this;
        }

        public Builder withExpression(Expression expression) {
            this.expression = expression;
            return this;
        }

        public AssignmentStatement build() {
            return new AssignmentStatement(line, lValue, expression);
        }
    }
}
