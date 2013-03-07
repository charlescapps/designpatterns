package capps.interpreter;

import capps.interpreter.expr.Expr;
import capps.interpreter.parser.ExpressionParser;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/29/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParseSimpleMain {
    public static void main(String[] args) throws Exception {
        ExpressionParser parser = ExpressionParser.getInstance();
        String exprStr = "5 + x - 2*2*5-z";
        Expr expr = parser.parseExpression(exprStr);
        System.out.println(expr);
    }
}
