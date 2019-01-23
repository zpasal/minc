package ba.compiler.minc.ast.nodes.statements;

import ba.compiler.minc.ast.nodes.AstNode;

import java.util.ArrayList;
import java.util.List;

public abstract class Statement extends AstNode {
    private List<Integer> nextList = new ArrayList<>();

    public Statement(int line) {
        super(line);
    }

    public List<Integer> getNextList() {
        return nextList;
    }

    public void setNextList(List<Integer> nextList) {
        this.nextList = nextList;
    }
}
