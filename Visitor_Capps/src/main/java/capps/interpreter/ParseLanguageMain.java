package capps.interpreter;

import capps.interpreter.expr.Var;
import capps.interpreter.parser.LanguageParser;
import capps.interpreter.statement.Statement;
import capps.interpreter.visitor.InterpretVisitor;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 3/5/13
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParseLanguageMain {
    public static void main(String[] args) throws Exception {
        String simple = "x := 5";
     //   parseCode(simple);

        String twoAssignments = "begin \n" +
                                "   x := -1;\n" +
                                "   y := 5;\n" +
                                "   print \"x = \";\n" +
                                "   print x\n" +
                                "end";
        parseCode(twoAssignments);

        String helloWorld = "print \"Hello, world!\n\"";
        parseCode(helloWorld);

        String factCode =
                "    begin\n" +
                "      n := 6;\n" +
                "      fact := 1;\n" +
                "      while (n > 1) do\n" +
                "        begin\n" +
                "          fact := fact * n;\n" +
                "          n := n - 1\n" +
                "        end;\n" +
                "      print \"fact = \";\n" +
                "      print fact;\n" +
                "      print \"\nn = \";\n" +
                "      print n\n" +
                "    end";
        parseCode(factCode);

    }

    private static void parseCode(String code) throws Exception {
        LanguageParser parser = LanguageParser.getInstance();
        Statement stmt = parser.parse(code);


        System.out.println("PROGRAM:");
        System.out.println(stmt);

        System.out.println("RUNNING PROGRAM...");
        InterpretVisitor visitor = new InterpretVisitor();
        stmt.accept(visitor);
        System.out.println();

    }
}
