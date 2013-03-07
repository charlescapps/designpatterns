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
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class While extends Statement {

    private Expr expr;
    private Statement statement;

    public While(Expr expr, Statement statement) {
        this.expr = expr; this.statement = statement;
    }

    public Expr getCondition() {
        return expr;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public String toString() {
        return "while ( " + expr + " ) do\n" +
                statement.toString(1);
    }

    @Override
    public LittleLangComponent[] components() {
        return new LittleLangComponent[] {
                expr, statement
        };  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void accept(StatementVisitorIF v) {
        v.visit(this);
    }
}
