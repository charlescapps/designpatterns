package capps.interpreter;

import capps.interpreter.lexer.Lexer;
import capps.interpreter.lexer.Token;
import capps.interpreter.lexer.TokenException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class LexerTestMain {

    public static void main(String [] args) throws TokenException {
        String code = "x + y - z *5+2*abc123";
        Lexer lexer = Lexer.getInstance();
        List<Token> tokens = lexer.scan(code);
        for (Token t: tokens) {
            System.out.println(t);
        }
    }
}
