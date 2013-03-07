package capps.interpreter.lexer;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/28/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SemicolonToken implements Token {
    @Override
    public Pair<Token, String> matchNext(String tokenString) {
        if (tokenString.length() <= 0 || tokenString.charAt(0) != ';') {
            return new Pair(null, tokenString);
        }
        return new Pair(new SemicolonToken(), tokenString.substring(1));
    }

    @Override
    public String toString() {
        return "( ; )";
    }
}
