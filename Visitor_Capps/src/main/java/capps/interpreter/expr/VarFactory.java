package capps.interpreter.expr;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class VarFactory {
    private static final VarFactory instance = new VarFactory();

    private VarFactory(){

    }

    public static VarFactory getInstance() {
        return instance;
    }

    private Map<String, Var> varPool = new HashMap<String, Var>();

    public Var getVar(String name) {

        if (varPool.get(name) != null) {
            return varPool.get(name);
        }
        varPool.put(name, new Var(name));
        return varPool.get(name);
    }

    public Var getVar(String name, int initialValue, Map<Var, Integer> vars) {

        if (varPool.get(name) != null) {
            return varPool.get(name);
        }
        Var v = new Var(name);
        varPool.put(name, v);
        vars.put(v, initialValue);
        return v;
    }
}
