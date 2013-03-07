package capps.interpreter;

import capps.interpreter.expr.*;
import capps.interpreter.statement.Assignment;
import capps.interpreter.statement.Compound;
import capps.interpreter.statement.Statement;
import capps.interpreter.statement.While;
import capps.interpreter.visitor.InterpretVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class InterpretVisitorMain {
    public static void main(String[] args) {
        VarFactory vf = VarFactory.getInstance();

        //initialize inputs.
        Var n = vf.getVar("n");              //For convenience, give these names
        Var result = vf.getVar("result");

        Statement fact =
            new Compound(
                new Assignment(n, new NumberExpr("10")),
                new Assignment(result, new NumberExpr("1")),
                new While(new GT(n, new NumberExpr("0")),
                    new Compound(
                       new Assignment(result, new OperationExpr(OP.MULT, result, n)),
                       new Assignment(n, new OperationExpr(OP.SUB, n, new NumberExpr("1")))
                    )
                )
            );

        System.out.println(fact);

        InterpretVisitor interpreter = new InterpretVisitor();
        fact.accept(interpreter);
        Map<Var, Integer> varsToValues = interpreter.getResult();

        for (Var v: varsToValues.keySet()) {
            System.out.println(v.prettyPrint() + " = " + varsToValues.get(v));
        }

    }
}