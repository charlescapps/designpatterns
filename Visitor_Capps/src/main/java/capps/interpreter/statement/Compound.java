package capps.interpreter.statement;

import capps.interpreter.LittleLangComponent;
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
public class Compound extends Statement {
    private Statement[] statements;
    public Compound(Statement ... statements) {
        this.statements = new Statement[statements.length];
        System.arraycopy(statements, 0, this.statements, 0, statements.length);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("begin\n");
        for (int i = 0; i < statements.length; i++) {
            sb.append(statements[i].toString(1));
            if (i < statements.length - 1) {
                sb.append(";");
            }
            sb.append("\n");
        }
        return sb.append("end\n").toString();
    }

    public Statement[] getStatements() {
        Statement[] copy = new Statement[statements.length];
        System.arraycopy(statements, 0, copy, 0, statements.length);
        return copy;
    }

    @Override
    public LittleLangComponent[] components() {
        return statements;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void accept(StatementVisitorIF v) {
        v.visit(this);
    }
}
