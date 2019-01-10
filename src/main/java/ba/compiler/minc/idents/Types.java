package ba.compiler.minc.idents;

import ba.compiler.minc.parser.MinCLexer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Types {

    public static Map<Integer, Integer> TYPE_SIZES = Stream.of(new Integer[][] {
            { MinCLexer.BoolType, 1 },
            { MinCLexer.IntType, 4 },
            { MinCLexer.CharType, 1 },
            { MinCLexer.RealType, 8 },
            { MinCLexer.VoidType, 0 }
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));


    /*

     Widening Hierarchy (implicit casting). Usually described as graph, e.g.:

     double -> float -> long -> int -> short -> char
                                             -> byte
                                    -> char
                                             -> short
                                             -> byte
                                    -> byte
                                             -> short
                                             -> char

     Implicit is available if there is path from node s to node t. In our case:

      ->    bool  int  char  real void
     bool   x     -    -     -    -
     int    -     x    -     x    -
     char   -     x    x     x    -
     real   -     -    -     x    -
     void   -     -    -     -    -

     */
    public static Map<Integer, List<Integer>> WIDENING_TYPES = new HashMap<Integer, List<Integer>>() {{
        put(MinCLexer.BoolType, Arrays.asList(MinCLexer.BoolType));
        put(MinCLexer.IntType, Arrays.asList(MinCLexer.IntType, MinCLexer.RealType));
        put(MinCLexer.CharType, Arrays.asList(MinCLexer.CharType, MinCLexer.IntType, MinCLexer.RealType));
        put(MinCLexer.RealType, Arrays.asList(MinCLexer.RealType));

    }};

    // implicit conversation by widening: double -> float -> long -> int -> char

    public static int calcWidth(int type) {
        if (TYPE_SIZES.containsKey(type)) {
            return TYPE_SIZES.get(type);
        }
        throw new RuntimeException("Unsupported type int" + type);
    }

    public static int calcWidth(int type, List<Integer> dimensions) {
        int baseWidth = calcWidth(type);
        if (dimensions == null || dimensions.isEmpty()) {
            return baseWidth;
        }
        return baseWidth * dimensions.stream().reduce(1, Math::multiplyExact);
    }

    public static int max(int type1, int type2) {
        if (!WIDENING_TYPES.containsKey(type1)) {
            throw new RuntimeException("Type cannot be used in expression " + type1);
        }
        if (!WIDENING_TYPES.containsKey(type2)) {
            throw new RuntimeException("Type cannot be used in expression " + type2);
        }
        return calcWidth(type1) >= calcWidth(type2) ? type1 : type2;
    }

}
