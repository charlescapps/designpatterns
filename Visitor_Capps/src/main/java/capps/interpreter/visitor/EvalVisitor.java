package capps.interpreter.visitor;

import capps.interpreter.expr.*;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/28/13
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class EvalVisitor implements ExprVisitorIF {

    protected Integer result;
    protected Map<Var, Integer> vars;

    public EvalVisitor(Map<Var,Integer> vars) {
        this.vars = vars;
    }

    public Integer getResult() {
         return result;
    }

    @Override
    public void visit(GT gt) {
        Expr left = gt.getLeft();
        Expr right = gt.getRight();
        left.accept(this);
        int leftResult = result;
        right.accept(this);
        int rightResult = result;
        if (leftResult > rightResult) {
            result = 1;
        }
        else {
            result = 0;
        }
    }

    @Override
    public void visit(LT lt) {
        Expr left = lt.getLeft();
        Expr right = lt.getRight();
        left.accept(this);
        int leftResult = result;
        right.accept(this);
        int rightResult = result;
        if (leftResult < rightResult) {
            result = 1;
        }
        else {
            result = 0;
        }
    }

    @Override
    public void visit(Var var) {
        Integer value = vars.get(var);
        if (value == null) {
            throw new RuntimeException("Attempt to evaluate uninitialized variable: " + var);
        }
        result = value;
    }

    @Override
    public void visit(NumberExpr numberExpr) {
        result = numberExpr.getValue();
    }

    @Override
    public void visit(OperationExpr operationExpr) {
        operationExpr.getLeft().accept(this);
        int leftResult = result;
        operationExpr.getRight().accept(this);
        int rightResult = result;

        switch (operationExpr.getOp()) {
            case ADD:
                result = leftResult + rightResult;
                break;
            case SUB:
                result = leftResult - rightResult;
                break;
            case MULT:
                result = leftResult * rightResult;
                break;
            case DIVIDE:
                result = leftResult / rightResult;
                break;
        }
    }
}
