package ba.compiler.minc.ast.nodes.expressions;

import ba.compiler.minc.ast.nodes.AstNode;
import ba.compiler.minc.intercode.instructions.Arg;

import java.util.ArrayList;
import java.util.List;

public abstract class Expression extends AstNode {
    // Synthesised attributes
    private Arg address;
    private int type;
    private int width;
    private List<Integer> trueList = new ArrayList<>();
    private List<Integer> falseList = new ArrayList<>();

    public Expression(int line) {
        super(line);
    }

    public Arg getAddress() {
        return address;
    }

    public void setAddress(Arg address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<Integer> getTrueList() {
        return trueList;
    }

    public void setTrueList(List<Integer> trueList) {
        this.trueList = trueList;
    }

    public List<Integer> getFalseList() {
        return falseList;
    }

    public void setFalseList(List<Integer> falseList) {
        this.falseList = falseList;
    }
}
