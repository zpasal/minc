package ba.compiler.minc.basicblocks;

import ba.compiler.minc.intercode.IntermediateCode;


public class BasicBlock {
    private int start;
    private int end;
    private IntermediateCode ic;

    public BasicBlock(int start, int end, IntermediateCode ic) {
        this.start = start;
        this.end = end;
        this.ic = ic;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public IntermediateCode getIc() {
        return ic;
    }

    public void setIc(IntermediateCode ic) {
        this.ic = ic;
    }

    public void dump() {
        System.out.println("BB-" + start + ":");
        for (int i=0; i<end - start; i++) {
            String instString = String.format("\t%07d %s",
                    start + i,
                    ic.getInstructions().get(start + i));
            System.out.println(instString);
        }
    }
}
