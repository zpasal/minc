package ba.compiler.minc.ast.nodes.expressions;

import java.util.List;

public class IdentifierExpression extends Expression {
    private String name;
    private List<Expression> indexes;

    public IdentifierExpression(int line, String name, List<Expression> indexes) {
        super(line);
        this.name = name;
        this.indexes = indexes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Expression> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Expression> indexes) {
        this.indexes = indexes;
    }


    public static final class Builder {
        private int line;
        private String name;
        private List<Expression> indexes;

        private Builder() {
        }

        public static Builder anIdentifierExpression() {
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

        public Builder withIndexes(List<Expression> indexes) {
            this.indexes = indexes;
            return this;
        }

        public IdentifierExpression build() {
            return new IdentifierExpression(line, name, indexes);
        }
    }
}
