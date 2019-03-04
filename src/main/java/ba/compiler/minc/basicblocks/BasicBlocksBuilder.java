package ba.compiler.minc.basicblocks;

import ba.compiler.minc.intercode.IntermediateCode;
import ba.compiler.minc.intercode.instructions.Opcode;
import ba.compiler.minc.intercode.instructions.QuadCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
Following statements of the code will always be called as leaders-

    1. First statement of the code
    2. Statement that is a target of the conditional goto statement or unconditional goto statement
    3. Statement that appears immediately after a goto statement
        (or Any instruction that immediately follows a conditional or unconditional jump is a leader.??)


    All the statements that follows the leader (including the leader itself) till just before
    the next leader appears forms one basic block.

    The block containing the first leader which is the first statement of the code is called as
    initial block.
*/
public class BasicBlocksBuilder {

    private IntermediateCode ic;
    private List<BasicBlock> basicBlocks = new ArrayList<>();

    public BasicBlocksBuilder(IntermediateCode ic) {
        this.ic = ic;
    }

    public void build() {
        List<Integer> leaderIndices = findLeaders();
        printLeaders(leaderIndices);

        buildBlocks(leaderIndices);
        printBlocks();
    }

    private void buildBlocks(List<Integer> leaderIndices) {
        assert leaderIndices.size() > 1 : "BasicBlocks: Code generated with less than 2 leader indices";

        for (int i=0; i<leaderIndices.size(); i++) {
            Integer start = leaderIndices.get(i);
            // If last block - end is end of IC
            Integer end = i != leaderIndices.size()-1 ?
                    leaderIndices.get(i+1) :
                    ic.getInstructions().size();
            BasicBlock newBlock = new BasicBlock(start, end, ic);
            basicBlocks.add(newBlock);
        }
    }

    private List<Integer> findLeaders() {
        List<Integer> leaders = new ArrayList<>();
        List<QuadCode> instructions = ic.getInstructions();

        for (int i=0; i<instructions.size(); i++) {
            QuadCode instruction = instructions.get(i);
            if (i == 0) {                                   // Rule 1
                leaders.add(i);
            }
            else if (instruction.isConditional()) {         // Rule 2
                leaders.addAll(instruction.getConditionalTargets());
            }
            else if (instruction.operand == Opcode.JUMP || instruction.operand == Opcode.EXIT) {  // Rule 3
                // check if there are instructions at all after JUMP
                if (instructions.size() > i+1) {
                    leaders.add(i + 1);
                }
            }
        }
        return leaders.stream()
                .sorted()
                .distinct()
                .collect(Collectors.toList());
    }

    private void printLeaders(List<Integer> leaderIndices) {
        System.out.println("Leaders:");
        leaderIndices.stream()
                .forEach(ind -> System.out.println(ind));
        System.out.println("End Leaders.");
    }

    private void printBlocks() {

        System.out.println("Basic Blocks:");
        basicBlocks.stream()
                .forEach(bb -> bb.dump());
        System.out.println("End Basic Blocks.");

    }

}
