package capps.interpreter.statement;

import capps.interpreter.LittleLangComponent;
import capps.interpreter.expr.Expr;
import capps.interpreter.visitor.ExprVisitorIF;
import capps.interpreter.visitor.StatementVisitorIF;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 3/5/13
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrintStatement extends Statement {
    private Expr exprToPrint;
    private String stringLiteral;

    public PrintStatement(Expr exprToPrint) {
        this.exprToPrint = exprToPrint;
    }

    public PrintStatement(String stringLiteral) {
        this.stringLiteral = stringLiteral.replace("\\n", "\n"); //Client can put \n in code and printing will actually print a newline.
    }

    @Override
    public LittleLangComponent[] components() {
        return new LittleLangComponent[] {exprToPrint};
    }

    @Override
    public void accept(StatementVisitorIF visitor) {
       visitor.visit(this);
    }

    public Expr getExprToPrint(){
        return exprToPrint;
    }

    public String getStringLiteral(){
        return stringLiteral;
    }

    @Override
    public String toString() {
        return "print " + (exprToPrint != null ? exprToPrint.toString() : "\"" + sanitize(stringLiteral) + "\"");
    }

    public static String sanitize(String s) {   //When displaying *code*, replace endlines with "\\n"
        return s.replace("\n", "\\n");
    }

}
