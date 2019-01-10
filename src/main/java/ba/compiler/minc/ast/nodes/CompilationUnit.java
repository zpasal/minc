package ba.compiler.minc.ast.nodes;

import ba.compiler.minc.ast.nodes.declarations.Declaration;
import ba.compiler.minc.idents.Env;

import java.util.List;

public class CompilationUnit extends AstNode {
    private List<Declaration> declarations;
    private Env env;

    public CompilationUnit(int line, List<Declaration> declarations) {
        super(line);
        this.declarations = declarations;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public static final class Builder {
        private int line;
        private List<Declaration> declarations;

        private Builder() {
        }

        public static Builder aCompilationUnit() {
            return new Builder();
        }

        public Builder withLine(int line) {
            this.line = line;
            return this;
        }

        public Builder withDeclarations(List<Declaration> declarations) {
            this.declarations = declarations;
            return this;
        }

        public CompilationUnit build() {
            return new CompilationUnit(line, declarations);
        }
    }
}
