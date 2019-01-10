package ba.compiler.minc;

import ba.compiler.minc.ast.AstBuilder;
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
        intermediateCodeGenerator.getIc().getInstructions().forEach(x -> System.out.println(x));
    }
}
