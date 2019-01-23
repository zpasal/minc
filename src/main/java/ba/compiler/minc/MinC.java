package ba.compiler.minc;

import ba.compiler.minc.ast.AstBuilder;
import ba.compiler.minc.ast.AstPrint;
import ba.compiler.minc.ast.nodes.CompilationUnit;
import ba.compiler.minc.idents.IdentTableVisitor;
import ba.compiler.minc.intercode.IntermediateCodeGenerator;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;

public class MinC {
    public static void main(String [ ] args) throws Exception {
        CharStream charStream = new ANTLRFileStream("src/main/java/ba/compiler/minc/examples/example1.mc");

        AstBuilder astBuilder = new AstBuilder();
        CompilationUnit compilationUnit = astBuilder.parse(charStream);

        IdentTableVisitor identTableVisitor = new IdentTableVisitor();
        identTableVisitor.visit(compilationUnit);

        IntermediateCodeGenerator intermediateCodeGenerator = new IntermediateCodeGenerator();
        intermediateCodeGenerator.visit(compilationUnit);
        for (int i=0; i<intermediateCodeGenerator.getIc().getInstructions().size(); i++) {
            String instString = String.format("%07d %s", i, intermediateCodeGenerator.getIc().getInstructions().get(i));
            System.out.println(instString);
        }

    }
}
