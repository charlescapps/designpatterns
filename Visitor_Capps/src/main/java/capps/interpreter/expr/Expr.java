package capps.interpreter.expr;

import capps.interpreter.LittleLangComponent;
import capps.interpreter.visitor.ExprVisitorIF;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Expr extends LittleLangComponent {

    public abstract void accept(ExprVisitorIF visitor);

}
