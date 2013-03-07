package capps.interpreter.expr;

import capps.interpreter.visitor.ExprVisitorIF;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class OperationExpr extends Expr {

    private OP op;
    private Expr operand1;
    private Expr operand2;

    public OperationExpr(OP op, Expr operand1, Expr operand2) {
        this.op = op;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Expr getLeft() {
        return operand1;
    }

    public Expr getRight() {
        return operand2;
    }

    public OP getOp() {
        return op;
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + op.toString() + operand2.toString() + ")";
    }

    @Override
    public void accept(ExprVisitorIF visitor) {
        visitor.visit(this);
    }

}
