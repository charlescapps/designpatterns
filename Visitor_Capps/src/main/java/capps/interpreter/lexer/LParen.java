package capps.interpreter.lexer;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class LParen implements Token {


    @Override
    public Pair<Token, String> matchNext(String tokenString){
        if (tokenString.charAt(0) == '(') {
            return new Pair( new LParen(), tokenString.substring(1) );
        }
        return new Pair(null, tokenString);
    }

    @Override
    public String toString() {return "(";}
}
