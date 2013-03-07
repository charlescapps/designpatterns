package capps.interpreter.lexer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/28/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
import static capps.interpreter.lexer.KeywordToken.KEYWORD;

public class Constants {

    public static final String KEYWORD_BEGIN = "begin";
    public static final String KEYWORD_ELSE = "else";
    public static final String KEYWORD_END = "end";
    public static final String KEYWORD_IF = "if";
    public static final String KEYWORD_THEN = "then";
    public static final String KEYWORD_WHILE = "while";

    public static final String[] KEYWORDS = new String[] {
            KEYWORD_BEGIN, KEYWORD_ELSE, KEYWORD_END, KEYWORD_IF,
            KEYWORD_THEN, KEYWORD_WHILE
    };

    public static final Map<String, KeywordToken.KEYWORD> stringToKeyword = new HashMap<String, KeywordToken.KEYWORD>();

    static {
        stringToKeyword.put(KEYWORD_BEGIN, KEYWORD.BEGIN);
        stringToKeyword.put(KEYWORD_ELSE, KEYWORD.ELSE);
        stringToKeyword.put(KEYWORD_END, KEYWORD.END);
        stringToKeyword.put(KEYWORD_IF, KEYWORD.IF);
        stringToKeyword.put(KEYWORD_THEN, KEYWORD.THEN);
        stringToKeyword.put(KEYWORD_WHILE, KEYWORD.WHILE);
    }
}
