package ba.compiler.minc.basicblocks;

import ba.compiler.minc.intercode.QuadCodeFormatter;
import ba.compiler.minc.intercode.instructions.QuadCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BasicBlock {
    private int name;
    private int address;
    private List<QuadCode> instructions = new ArrayList<>();

    public BasicBlock(int name, int address) {
        this.name = name;
        this.address = address; // name is same as address
    }

    public int getName() {
        return name;
    }

    public int getAddress() {
        return address;
    }

    public List<QuadCode> getInstructions() {
        return instructions;
    }

    public void addInstruction(QuadCode quadCode) {
        instructions.add(quadCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BB-")
                .append(name)
                .append(":\n");
        for (int i=0; i<instructions.size(); i++) {
            QuadCode instruction = instructions.get(i);
            String instString = String.format("\t%07d %s",
                    address + i,
                    QuadCodeFormatter.toString(instruction));
            sb.append(instString)
                    .append("\n");
        }
        return sb.toString();
    }

    public void dump() {
        System.out.println(toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasicBlock bb = (BasicBlock)o;
        return Objects.equals(address, bb.address) &&
                Objects.equals(name, bb.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, name);
    }
}
