package capps.interpreter.expr;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public enum OP {
    ADD, SUB, MULT, DIVIDE;
     public String toString() {
         switch (this) {
             case ADD: return "+";
             case SUB: return "-";
             case MULT: return "*";
             case DIVIDE: return "/";
         }
         return null;
     }
}