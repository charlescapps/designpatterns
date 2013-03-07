package capps.interpreter.statement;

import capps.interpreter.LittleLangComponent;
import capps.interpreter.expr.Var;
import capps.interpreter.visitor.StatementVisitorIF;

import java.util.Map;

/**
 * A Statement is a Composite
 */
public abstract class Statement extends LittleLangComponent {
    public abstract LittleLangComponent[] components();
    public abstract void accept(StatementVisitorIF v);
}
