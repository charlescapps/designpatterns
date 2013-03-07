package capps.interpreter.lexer;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 3/5/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class GTToken implements Token {
    @Override
    public Pair<Token, String> matchNext(String input) {
        if (input.startsWith(">")) {
            return new Pair<Token, String>(new GTToken(), input.substring(1));
        }
        return new Pair(null, input);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
