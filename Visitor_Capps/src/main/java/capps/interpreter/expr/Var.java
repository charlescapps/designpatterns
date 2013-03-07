package capps.interpreter.expr;

import capps.interpreter.visitor.ExprVisitorIF;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Var extends LiteralExpr {

    private String name;

    public Var(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String prettyPrint() {
        return "a variable \"" + name + "\"";
    }

    @Override
    public boolean equals(Object o) {
        return (this == o) || (o instanceof Var) && o.toString().equals(this.toString());
    }

    @Override
    public void accept(ExprVisitorIF visitor) {
        visitor.visit(this);
    }

}
