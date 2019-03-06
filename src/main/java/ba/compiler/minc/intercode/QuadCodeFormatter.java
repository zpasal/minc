package ba.compiler.minc.intercode;

import ba.compiler.minc.intercode.instructions.*;

public class QuadCodeFormatter {
    public static String toString(QuadCode quadCode) {
        String s = String.format("%1$-10s", quadCode.operand.name());
        if (quadCode.arg1 != null) {
            s += argToString(quadCode.arg1);
        }
        if (quadCode.arg2 != null) {
            s += ", " +  argToString(quadCode.arg2);
        }
        if (quadCode.result != null) {
            s += ", " +  argToString(quadCode.result);
        }
        return s;
    }

    private static String argToString(Arg arg) {
        if (arg instanceof ArgBoolean) {
            return ""+((ArgBoolean)arg).value ;
        }
        else if (arg instanceof ArgIdentifier) {
            return ((ArgIdentifier)arg).name;
        }
        else if (arg instanceof ArgInteger) {
            return ""+((ArgInteger)arg).value ;
        }
        else if (arg instanceof ArgLabel) {
            int address = ((ArgLabel)arg).address;
            String label = null; //ic.getLabelAt(address);
            return label != null ? label : "@BB_"+address;
        }
        else if (arg instanceof ArgReal) {
            return "" + ((ArgReal)arg).value ;
        }
        else if (arg instanceof ArgTemp) {
            return "T" + ((ArgTemp)arg).temp ;
        }
        else {
            assert false : "Unsupported arg type : " + arg.getClass().getCanonicalName();
            return "";
        }
    }
}
