package ba.compiler.minc.ast.nodes.declarations;

import ba.compiler.minc.idents.Types;

import java.util.List;

public class VarDeclaration extends Declaration {
    private String name;
    private List<Integer> dimensions;
    private int type;
    private int width;

    public VarDeclaration(int line, String name, int type, List<Integer> dimensions) {
        super(line);
        this.name = name;
        this.type = type;
        this.dimensions = dimensions;
        this.width = Types.calcWidth(type, dimensions);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static final class Builder {
        private int line;
        private String name;
        private int type;
        private List<Integer> dimensions;

        private Builder() {
        }

        public static Builder aVarDeclaration() {
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

        public Builder withType(int type) {
            this.type = type;
            return this;
        }

        public Builder withDimensions(List<Integer> dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public VarDeclaration build() {
            return new VarDeclaration(line, name, type, dimensions);
        }
    }
}
