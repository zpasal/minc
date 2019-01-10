package ba.compiler.minc.ast.nodes.declarations;

import java.util.List;

public class Type {
    private int type;
    private List<Integer> dimensions;

    public Type(int type, List<Integer> dimensions) {
        this.type = type;
        this.dimensions = dimensions;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Integer> dimensions) {
        this.dimensions = dimensions;
    }


    public static final class Builder {
        private int type;
        private List<Integer> dimensions;

        private Builder() {
        }

        public static Builder aType() {
            return new Builder();
        }

        public Builder withType(int type) {
            this.type = type;
            return this;
        }

        public Builder withDimensions(List<Integer> dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Type build() {
            return new Type(type, dimensions);
        }
    }
}
