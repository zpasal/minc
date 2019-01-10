package ba.compiler.minc.ast.nodes.statements;

import ba.compiler.minc.ast.nodes.expressions.Expression;

import java.util.List;

public class LValue {
    private String name;
    private List<Expression> indexes;

    public LValue(String name, List<Expression> indexes) {
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
        private String name;
        private List<Expression> indexes;

        private Builder() {
        }

        public static Builder aLValue() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withIndexes(List<Expression> indexes) {
            this.indexes = indexes;
            return this;
        }

        public LValue build() {
            return new LValue(name, indexes);
        }
    }
}
