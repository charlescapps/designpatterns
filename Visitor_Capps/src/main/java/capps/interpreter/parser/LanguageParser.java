package capps.interpreter.parser;

import capps.interpreter.expr.Expr;
import capps.interpreter.expr.VarFactory;
import capps.interpreter.lexer.*;
import capps.interpreter.statement.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 3/5/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class LanguageParser extends AbstractParser{
    private VarFactory vf = VarFactory.getInstance();
    private ExpressionParser expressionParser = new ExpressionParser();

    private static final LanguageParser instance = new LanguageParser();

    public static LanguageParser getInstance()  {
        return instance;
    }

    private LanguageParser(){
        super();
    }

    public Statement parse(String code) throws TokenException, ParseException {
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.scan(code);
        return parse(tokens);
    }

    public Statement parse(List<Token> tokens) throws ParseException {
        Statement s = statement(tokens);
        if (s == null) {
            throw new ParseException("Failed to parse as a statement!");
        }
        require(tokens, EndToken.class);
        return s;
    }

    public Statement statement(List<Token> tokens) throws ParseException {
        Statement s = compound(tokens);
        if (s != null) {
            return s;
        }
        s = assignment(tokens);
        if (s != null) {
            return s;
        }
        s = conditional(tokens);
        if (s != null) {
            return s;
        }
        s = parsePrint(tokens);
        if (s != null){
            return s;
        }
        return parseWhile(tokens);
    }

    public PrintStatement parsePrint(List<Token> tokens) throws ParseException {
        KeywordToken token = (KeywordToken)peek(tokens, KeywordToken.class);
        if (token == null || token.keyword != KeywordToken.KEYWORD.PRINT) {
            return null;
        }
        tokens.remove(0);
        Expr printExpr = null;
        try {
            printExpr = expressionParser.expr(tokens);
        } catch (ParseException e) {
            printExpr = null;
        }
        if (printExpr !=  null) {
            return new PrintStatement(printExpr);
        }
        else {
            StringLiteralToken str = (StringLiteralToken)require(tokens, StringLiteralToken.class);
            return new PrintStatement(str.stringValue);
        }
    }

    public While parseWhile(List<Token> tokens) throws ParseException {
        KeywordToken token = (KeywordToken)peek(tokens, KeywordToken.class);
        if (token == null || token.keyword != KeywordToken.KEYWORD.WHILE) {
            return null;
        }
        tokens.remove(0); //peeked so manually remove first token
        require(tokens, LParen.class);
        Expr expr = expressionParser.expr(tokens);
        require(tokens, RParen.class);
        token = (KeywordToken)require(tokens, KeywordToken.class);
        if (token.keyword != KeywordToken.KEYWORD.DO) {
            throw new ParseException("While statement without matching 'do' keyword");
        }
        Statement doStmt = statement(tokens);
        if (doStmt == null) {
            throw new ParseException("While statement missing 'do' block.");
        }
        return new While(expr, doStmt);
    }

    public Conditional conditional(List<Token> tokens) throws ParseException {
         KeywordToken token = (KeywordToken)peek(tokens, KeywordToken.class);
         if (token == null || token.keyword != KeywordToken.KEYWORD.IF) {
             return null;
         }
        tokens.remove(0); //peeked to check if was correct keyword, so must manually remove.

        Expr expr = expressionParser.expr(tokens);

        token = (KeywordToken)require(tokens, KeywordToken.class);
        if (token == null || token.keyword != KeywordToken.KEYWORD.THEN) {
            throw new ParseException("If statement without matching 'then'");
        }
        Statement ifStmt = statement(tokens);
        token = (KeywordToken)require(tokens, KeywordToken.class);
        if (token == null || token.keyword != KeywordToken.KEYWORD.ELSE) {
            throw new ParseException("If statement without matching 'else'");
        }
        Statement thenStmt = statement(tokens);
        return new Conditional(expr, ifStmt, thenStmt);
    }

    public Compound compound(List<Token> tokens) throws ParseException {
        KeywordToken begin = (KeywordToken)peek(tokens, KeywordToken.class);
        if (begin == null || begin.keyword != KeywordToken.KEYWORD.BEGIN) {
            return null;
        }
        tokens.remove(0); //peeked so must manually remove.
        List<Statement> stmts = new ArrayList<Statement>();
        Statement nextStatement;
        while(true) {
            nextStatement = statement(tokens);
            if (nextStatement == null) {
                throw new ParseException("Compound statement with 0 statements or missing statement after ';'");
            }
            stmts.add(nextStatement);
            SemicolonToken semi = (SemicolonToken)expecting(tokens, SemicolonToken.class);
            if (semi == null) {
                break;
            }
        }

        KeywordToken endToken = (KeywordToken)require(tokens,KeywordToken.class); //Must have since we had a begin
        if (endToken.keyword != KeywordToken.KEYWORD.END) {
            throw new ParseException("Compound statement missing 'end' token");
        }
        return new Compound(stmts.toArray(new Statement[0]));
    }

    public Assignment assignment(List<Token> tokens) throws ParseException {
        Identifier id = (Identifier)expecting(tokens, Identifier.class);
        if (id == null) {
            return null;
        }
        require(tokens, AssignToken.class);
        Expr rValue = expressionParser.expr(tokens);
        return new Assignment(vf.getVar(id.getName()), rValue);
    }
}
