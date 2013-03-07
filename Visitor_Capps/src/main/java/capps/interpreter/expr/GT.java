package capps.interpreter.expr;

import capps.interpreter.visitor.ExprVisitorIF;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class GT extends Expr {
    private Expr expr1;
    private Expr expr2;

    public GT(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public Expr getLeft() {
        return expr1;
    }

    public Expr getRight() {
        return expr2;
    }

    @Override
    public void accept(ExprVisitorIF visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return expr1.toString() + " > " + expr2.toString();
    }
}
