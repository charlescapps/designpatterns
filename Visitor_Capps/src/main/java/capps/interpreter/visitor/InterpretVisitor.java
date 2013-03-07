package capps.interpreter.visitor;

import capps.interpreter.expr.Expr;
import capps.interpreter.expr.Var;
import capps.interpreter.statement.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/28/13
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class InterpretVisitor implements StatementVisitorIF {
    protected Map<Var, Integer> vars;

    public InterpretVisitor() {
        vars = new HashMap<Var, Integer>();
    }

    public Map<Var, Integer> getResult() {
        return vars;
    }

    @Override
    public void visit(Assignment assignment) {
        EvalVisitor evalVisitor = new EvalVisitor(vars);     //Use an Expression Visitor to evaluate the expression
        assignment.getRight().accept(evalVisitor);
        vars.put(assignment.getLeft(), evalVisitor.getResult());
    }

    @Override
    public void visit(Compound compound) {
        for (Statement s: compound.getStatements()) {
            s.accept(this);
        }
    }

    @Override
    public void visit(Conditional conditional) {
        EvalVisitor evalVisitor = new EvalVisitor(vars);
        conditional.getExpr().accept(evalVisitor);
        int result = evalVisitor.getResult();
        if (result == 1) {
            conditional.getIfTrue().accept(this);
        }
        else {
            conditional.getIfFalse().accept(this);
        }
    }

    @Override
    public void visit(While whileStmt) {
        EvalVisitor evalVisitor = new EvalVisitor(vars);
        whileStmt.getCondition().accept(evalVisitor);
        while (evalVisitor.getResult() == 1) {
            whileStmt.getStatement().accept(this);
            evalVisitor = new EvalVisitor(vars);
            whileStmt.getCondition().accept(evalVisitor);
        }
    }

    @Override
    public void visit(PrintStatement printStmt) {
        Expr expr = printStmt.getExprToPrint();
        if (expr != null) {
            EvalVisitor v = new EvalVisitor(vars);
            expr.accept(v);
            System.out.print(v.getResult());
        }
        else {
            System.out.print(printStmt.getStringLiteral());
        }
    }
}
