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
public class Identifier implements Token {
    private String name;
    private static final Pattern varPattern = Pattern.compile("([a-zA-Z]\\w*)");


    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public Pair<Token, String> matchNext(String input) {
        Matcher matcher = varPattern.matcher(input);
        if (matcher.lookingAt()) {
            String id = matcher.group(1);
            try {
                KeywordToken.KEYWORD keyword = KeywordToken.KEYWORD.valueOf(id.toUpperCase());
                return new Pair(null, input); //Return null, cannot use a keyword as an identifier.
            } catch (IllegalArgumentException e) {
                //Do nothing, we successfully found a variable that wasn't a keyword.
            }
            return new Pair(new Identifier(id),
                            input.substring(id.length()));
        }
        return new Pair(null, input);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(an identifier \"" + name + "\")";
    }
}
