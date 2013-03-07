package capps.interpreter.lexer;

import java.security.KeyPair;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Token {

    public Pair<Token, String> matchNext(String tokenString);
}
