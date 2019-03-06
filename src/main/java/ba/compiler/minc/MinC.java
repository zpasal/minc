package ba.compiler.minc;

import ba.compiler.minc.ast.AstBuilder;
import ba.compiler.minc.ast.nodes.CompilationUnit;
import ba.compiler.minc.basicblocks.BasicBlocksBuilder;
import ba.compiler.minc.flowgraph.FlowGraphBuilder;
import ba.compiler.minc.idents.IdentTableVisitor;
import ba.compiler.minc.intercode.IntermediateCodeGenerator;
import ba.compiler.minc.intercode.QuadCodeFormatter;
import ba.compiler.minc.intercode.instructions.QuadCode;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;

public class MinC {
    public static void main(String[] args) throws Exception {
        CharStream charStream = new ANTLRFileStream("src/main/java/ba/compiler/minc/examples/example2.mc");

        AstBuilder astBuilder = new AstBuilder();
        CompilationUnit compilationUnit = astBuilder.parse(charStream);

        IdentTableVisitor identTableVisitor = new IdentTableVisitor();
        identTableVisitor.visit(compilationUnit);

        IntermediateCodeGenerator intermediateCodeGenerator = new IntermediateCodeGenerator();
        intermediateCodeGenerator.visit(compilationUnit);
        for (int i=0; i<intermediateCodeGenerator.getIc().getInstructions().size(); i++) {
            String label = intermediateCodeGenerator.getIc().getLabelAt(i);
            if (label != null) {
                System.out.println(label + ":");
            }
            QuadCode instruction = intermediateCodeGenerator.getIc().getInstructions().get(i);
            String instString = String.format("\t%07d %s", i, QuadCodeFormatter.toString(instruction));
            System.out.println(instString);
        }

        BasicBlocksBuilder basicBlocksBuilder = new BasicBlocksBuilder(intermediateCodeGenerator.getIc());
        basicBlocksBuilder.build();

        FlowGraphBuilder flowGraphBuilder = new FlowGraphBuilder();
        flowGraphBuilder.build(basicBlocksBuilder.getBasicBlocks());
        flowGraphBuilder.visualize();

    }
}
