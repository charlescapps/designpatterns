package capps.interpreter.lexer;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/29/13
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class EndToken implements Token{
    @Override
    public Pair<Token, String> matchNext(String tokenString) {
        if (tokenString.trim().length() == 0) {
            return new Pair(new EndToken(), "");
        }
        return new Pair(null, tokenString);
    }

    @Override
    public String toString() {
        return "an EndToken";
    }
}
