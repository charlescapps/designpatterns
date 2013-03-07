package capps.interpreter.parser;

import capps.interpreter.lexer.Token;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 3/5/13
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractParser {
    protected Token peek(List<Token> tokens, Class tokenClass) {
        if (tokens.size() > 0 && tokens.get(0).getClass().equals(tokenClass)) {
            return tokens.get(0);
        }
        return null;
    }

    protected Token expecting(List<Token> ts, Class tokenClass)  {
        if (!(ts.get(0).getClass().equals(tokenClass))) {
            return null;
        }

        Token t = ts.get(0);
        ts.remove(0);
        return t;
    }

    protected Token require(List<Token> ts, Class tokenClass) throws ParseException {
        if (!(ts.get(0).getClass().equals(tokenClass))) {
            throw new ParseException("Required token class " + tokenClass.getName() + " at token " + ts.get(0));
        }
        Token t = ts.get(0);
        ts.remove(0);
        return t;
    }
}
