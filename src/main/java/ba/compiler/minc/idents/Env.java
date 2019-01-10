package ba.compiler.minc.idents;

import ba.compiler.minc.ast.nodes.declarations.VarDeclaration;

import java.util.HashMap;
import java.util.Map;

public class Env {
    private Map<String, VarDeclaration> table;
    private Env prev;

    public Env(Env p) {
        this.table = new HashMap<>();
        this.prev = p;
    }

    public void add(VarDeclaration varDeclaration) {
        String name = varDeclaration.getName();
        if (table.containsKey(name)) {
            throw new RuntimeException("Identifier already exists " + name);
        }
        table.put(name, varDeclaration);
    }

    public VarDeclaration get(String name) {
        for (Env e = this; e != null; e = e.prev) {
            VarDeclaration found = e.table.get(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

}
