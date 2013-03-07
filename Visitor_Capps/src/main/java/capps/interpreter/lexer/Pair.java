package capps.interpreter.lexer;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Pair<T, V> {
    private T A;
    private V B;
    public Pair(T A, V B) {
        this.A = A;
        this.B = B;
    }

    public T A() {return A;}
    public V B() {return B;}
}
