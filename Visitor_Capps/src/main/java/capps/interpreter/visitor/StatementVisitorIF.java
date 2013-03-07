package capps.interpreter.visitor;

import capps.interpreter.statement.*;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/28/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StatementVisitorIF {
    void visit(Assignment assignment);
    void visit(Compound compound);
    void visit(Conditional conditional);
    void visit(While whileStmt);
    void visit(PrintStatement printStmt);
}
