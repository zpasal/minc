package ba.compiler.minc.ast.nodes.expressions;

import java.util.List;

public class FunctionCallExpression extends Expression {
    private String name;
    private List<Expression> arguments;
    private List<Expression> returnIndexes;

    public FunctionCallExpression(int line, String name, List<Expression> arguments, List<Expression> returnIndexes) {
        super(line);
        this.name = name;
        this.arguments = arguments;
        this.returnIndexes = returnIndexes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public void setArguments(List<Expression> arguments) {
        this.arguments = arguments;
    }

    public List<Expression> getReturnIndexes() {
        return returnIndexes;
    }

    public void setReturnIndexes(List<Expression> returnIndexes) {
        this.returnIndexes = returnIndexes;
    }


    public static final class Builder {
        private int line;
        private String name;
        private List<Expression> arguments;
        private List<Expression> returnIndexes;

        private Builder() {
        }

        public static Builder aFunctionCallExpression() {
            return new Builder();
        }

        public Builder withLine(int line) {
            this.line = line;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withArguments(List<Expression> arguments) {
            this.arguments = arguments;
            return this;
        }

        public Builder withReturnIndexes(List<Expression> returnIndexes) {
            this.returnIndexes = returnIndexes;
            return this;
        }

        public FunctionCallExpression build() {
            return new FunctionCallExpression(line, name, arguments, returnIndexes);
        }
    }
}
