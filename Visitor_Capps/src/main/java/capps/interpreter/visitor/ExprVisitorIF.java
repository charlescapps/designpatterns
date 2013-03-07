package capps.interpreter.visitor;

import capps.interpreter.expr.*;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/28/13
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ExprVisitorIF {
    void visit(GT gt);
    void visit(LT lt);
    void visit(Var var);
    void visit(NumberExpr numberExpr);
    void visit(OperationExpr operationExpr);

}
