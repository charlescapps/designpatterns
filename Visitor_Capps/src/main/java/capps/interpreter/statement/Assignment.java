package capps.interpreter.statement;

import capps.interpreter.LittleLangComponent;
import capps.interpreter.expr.Expr;
import capps.interpreter.expr.Var;
import capps.interpreter.visitor.StatementVisitorIF;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment extends Statement {
    private Var var;
    private Expr expr;

    public Assignment(Var var, Expr expr) {
        this.var = var;
        this.expr = expr;
    }

    public Var getLeft() {
        return var;
    }

    public Expr getRight() {
        return expr;
    }

    @Override
    public String toString() {
        return var + " := " + expr;
    }

    @Override
    public LittleLangComponent[] components() {
        return new LittleLangComponent[] {
                var, expr
        };
    }

    @Override
    public void accept(StatementVisitorIF v) {
        v.visit(this);
    }
}
