package capps.interpreter;

import capps.interpreter.expr.Var;
import capps.interpreter.parser.LanguageParser;
import capps.interpreter.statement.Statement;
import capps.interpreter.visitor.InterpretVisitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 3/7/13
 * Time: 8:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Run {

    public static void main(String[] args) throws Exception {
        if (args.length <1 ) {
            System.err.println("Must provide a filename to interpret!");
            System.exit(1);
        }

        String code = inputFile(new File(args[0]));

        LanguageParser parser = LanguageParser.getInstance();

        Statement AST = parser.parse(code);

        InterpretVisitor interpreter = new InterpretVisitor();

        AST.accept(interpreter);

    }

    private static String inputFile(File f) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }

        return sb.toString();
    }
}
