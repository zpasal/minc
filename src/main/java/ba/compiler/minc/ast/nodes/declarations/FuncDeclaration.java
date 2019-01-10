package ba.compiler.minc.ast.nodes.declarations;

import ba.compiler.minc.ast.nodes.Block;
import ba.compiler.minc.idents.Env;

import java.util.List;

public class FuncDeclaration extends Declaration {
    private String name;
    private List<VarDeclaration> params;
    private int returnType;
    private List<Integer> returnTypeDimensions;
    private Block block;
    private Env env;

    public FuncDeclaration(int line, String name, List<VarDeclaration> params, int returnType, List<Integer> returnTypeDimensions, Block block) {
        super(line);
        this.name = name;
        this.params = params;
        this.returnType = returnType;
        this.returnTypeDimensions = returnTypeDimensions;
        this.block = block;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VarDeclaration> getParams() {
        return params;
    }

    public void setParams(List<VarDeclaration> params) {
        this.params = params;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    public List<Integer> getReturnTypeDimensions() {
        return returnTypeDimensions;
    }

    public void setReturnTypeDimensions(List<Integer> returnTypeDimensions) {
        this.returnTypeDimensions = returnTypeDimensions;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public static final class Builder {
        private int line;
        private String name;
        private List<VarDeclaration> params;
        private int returnType;
        private List<Integer> returnTypeDimensions;
        private Block block;

        private Builder() {
        }

        public static Builder aFuncDeclaration() {
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

        public Builder withParams(List<VarDeclaration> params) {
            this.params = params;
            return this;
        }

        public Builder withReturnType(int returnType) {
            this.returnType = returnType;
            return this;
        }

        public Builder withReturnTypeDimensions(List<Integer> returnTypeDimensions) {
            this.returnTypeDimensions = returnTypeDimensions;
            return this;
        }

        public Builder withBlock(Block block) {
            this.block = block;
            return this;
        }

        public FuncDeclaration build() {
            return new FuncDeclaration(line, name, params, returnType, returnTypeDimensions, block);
        }
    }
}
