package ba.compiler.minc.ast.nodes;

import ba.compiler.minc.ast.nodes.declarations.Declaration;
import ba.compiler.minc.ast.nodes.statements.Statement;

import java.util.List;

public class Block extends AstNode {
    private List<Declaration> declarations;
    private List<Statement> statements;
    private List<Integer> nextList;

    public Block(int line, List<Declaration> declarations, List<Statement> statements) {
        super(line);
        this.declarations = declarations;
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    public List<Integer> getNextList() {
        return nextList;
    }

    public void setNextList(List<Integer> nextList) {
        this.nextList = nextList;
    }


    public static final class Builder {
        private int line;
        private List<Declaration> declarations;
        private List<Statement> statements;
        private List<Integer> nextList;

        private Builder() {
        }

        public static Builder aBlock() {
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

        public Builder withStatements(List<Statement> statements) {
            this.statements = statements;
            return this;
        }

        public Builder withNextList(List<Integer> nextList) {
            this.nextList = nextList;
            return this;
        }

        public Block build() {
            Block block = new Block(line, declarations, statements);
            block.setNextList(nextList);
            return block;
        }
    }
}
