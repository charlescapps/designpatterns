package capps.interpreter.lexer;

import capps.interpreter.expr.OP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Lexer {
    private static Lexer instance = new Lexer();
    public static Lexer getInstance() {
        return instance;
    }

    public static final Token[] prototypes = new Token[] {
        new LParen(), new RParen(), new NumberToken(0), new Identifier("abc"), new OpToken(OP.MULT), new EndToken(),
            new KeywordToken(), new AssignToken(), new SemicolonToken(), new GTToken(), new LTToken(), new StringLiteralToken("")
    };

    public List<Token> scan(String input) throws TokenException{
        List<Token> tokens = new ArrayList<Token>();

        while (!input.equals("")) {
            input = input.trim();
            boolean foundMatch = false;
            for (Token prototype: prototypes) {
                Pair<Token, String> match = prototype.matchNext(input);
                if (match.A() != null) {
                    tokens.add(match.A());
                    input = match.B();
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                throw new TokenException("Invalid token found at: " + input.split("\\s+")[0]);
            }
        }
        if (! (tokens.get(tokens.size() - 1) instanceof EndToken) ) {
            tokens.add(new EndToken());
        }
        return tokens;
    }
}
