package capps.interpreter.expr;

import capps.interpreter.visitor.ExprVisitorIF;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class NumberExpr extends LiteralExpr {

    private int value;

    public int getValue() {
        return value;
    }

    @Override
    public String toString()  {
        return String.valueOf(value);
    }

    public NumberExpr(String value) {
        this.value = Integer.parseInt(value);
    }

    public NumberExpr(int value) {
        this.value = value;
    }

    @Override
    public void accept(ExprVisitorIF visitor) {
        visitor.visit(this);
    }
}
