package capps.interpreter.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/28/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeywordToken implements Token {
    public static final Pattern wordPattern = Pattern.compile("[a-z]+");

    public KEYWORD keyword;

    public KeywordToken() {
    }

    public KeywordToken(KEYWORD keyword) {
        this.keyword = keyword;
    }

    @Override
    public Pair<Token, String> matchNext(String tokenString) {
        Matcher m = wordPattern.matcher(tokenString);
        if (!m.lookingAt()) {
            return new Pair(null, tokenString);
        }
        String keywordStr = m.group(0);
        KEYWORD keyword;
        try {
            keyword = KEYWORD.valueOf(keywordStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new Pair(null, tokenString);
        }
        return new Pair(new KeywordToken(keyword), tokenString.substring(keywordStr.length()));
    }

    public static enum KEYWORD {
        BEGIN, END, ELSE, IF, THEN, WHILE, DO, PRINT
    };

    public String toString() {
        return "(a keyword \"" + keyword + "\")";
    }
}
