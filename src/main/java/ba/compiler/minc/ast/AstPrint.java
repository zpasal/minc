package ba.compiler.minc.ast;

import ba.compiler.minc.ast.nodes.CompilationUnit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AstPrint {
    public static void print(CompilationUnit compilationUnit) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(compilationUnit);
        System.out.println(json);
    }
}
