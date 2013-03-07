package capps.interpreter.lexer;

import capps.interpreter.expr.OP;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpToken implements Token {

    private OP type;

    public OpToken(OP type) {
        this.type = type;
    }

    public OP getType() {
        return type;
    }

    @Override
    public Pair<Token, String> matchNext(String tokenString) {
        String next = tokenString.substring(1);
        if (tokenString.startsWith("+")) {
            return new Pair(new OpToken(OP.ADD), next);
        }
        else if (tokenString.startsWith("*")) {
            return new Pair(new OpToken(OP.MULT), next);
        }
        else if (tokenString.startsWith("/")) {
            return new Pair(new OpToken(OP.DIVIDE), next);
        }
        else if (tokenString.startsWith("-")) {
            return new Pair(new OpToken(OP.SUB), next);
        }
        return new Pair(null, tokenString);
    }

    @Override
    public String toString() {
        return "an op(" + type + ")";
    }


}
