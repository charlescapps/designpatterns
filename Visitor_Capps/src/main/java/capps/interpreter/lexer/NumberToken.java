package capps.interpreter.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class NumberToken implements Token{
    private int value;
    private static final Pattern numPattern = Pattern.compile("\\d+");

    public NumberToken(int value)  {
        this.value = value;
    }

    @Override
    public Pair<Token, String> matchNext(String tokenString) {
        int val;
        Matcher m = numPattern.matcher(tokenString);
        if (m.lookingAt())    {
            try {
                val = Integer.parseInt(m.group(0));
            } catch (NumberFormatException e) {
                return new Pair(null, tokenString);
            }
        }
        else {
            return new Pair(null, tokenString);
        }

        return new Pair(new NumberToken(val), tokenString.substring(m.group(0).length()));
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "a number(" + value + ")";
    }
}
