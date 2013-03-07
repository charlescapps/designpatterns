package capps.interpreter.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 3/5/13
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringLiteralToken implements Token {
    Pattern stringLiteralPatt = Pattern.compile("\"([^\"]*)\"");

    public final String stringValue;

    public StringLiteralToken(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public Pair<Token, String> matchNext(String input) {
        Matcher m = stringLiteralPatt.matcher(input);
        if (!m.lookingAt()) {
            return new Pair(null, input);  //To change body of implemented methods use File | Settings | File Templates.
        }
        return new Pair(new StringLiteralToken(m.group(1)), input.substring(m.group(0).length()));

    }
}
