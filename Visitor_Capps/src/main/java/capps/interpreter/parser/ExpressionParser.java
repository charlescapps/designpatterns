package capps.interpreter.parser;

import capps.interpreter.expr.*;
import capps.interpreter.lexer.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExpressionParser extends AbstractParser{
    private static final ExpressionParser instance = new ExpressionParser();
    private static final VarFactory vf = VarFactory.getInstance();

    public static ExpressionParser getInstance() {
        return instance;
    }

    public Expr parseExpression(String code) throws ParseException, TokenException {
        Lexer lexer = Lexer.getInstance();
        List<Token> tokens = lexer.scan(code);
        return parseExpression(tokens);
    }

    public Expr parseExpression(List<Token> tokens) throws ParseException {
        Expr expr = expr(tokens);
        end(tokens);
        return expr;
    }

    public Expr expr(List<Token> tokens) throws ParseException {

        //try to parseExpression as factor
        Expr factor1 = factor(tokens);

        LTToken lt = (LTToken)expecting(tokens, LTToken.class);
        if (lt != null) {
            Expr factor2 = factor(tokens);
            return new LT(factor1, factor2);
        }

        GTToken gt = (GTToken)expecting(tokens, GTToken.class);
        if (gt != null) {
            Expr factor2 = factor(tokens);
            return new GT(factor1, factor2);
        }

        //2 or more factors.
        OpToken opToken = (OpToken)peek(tokens, OpToken.class);
        if (opToken == null || opToken.getType() != OP.ADD && opToken.getType() != OP.SUB) {
            return factor1;
        }

        tokens.remove(0);
        return new OperationExpr(opToken.getType(), factor1, expr(tokens));

    }

    private Expr factor(List<Token> tokens) throws ParseException {

        Expr leftOperand = parseSimple(tokens);

        OpToken opToken = (OpToken)peek(tokens, OpToken.class);
        if (opToken == null || opToken.getType() != OP.MULT && opToken.getType() != OP.DIVIDE) {
            return leftOperand; //only 1 factor
        }
        //2 or more factors
        tokens.remove(0);
        return new OperationExpr(opToken.getType(), leftOperand, factor(tokens));
    }

    private Expr parseSimple(List<Token> tokens) throws ParseException {
        //try to parse expression as identifier
        Expr var = varExpr(tokens);
        if (var != null) {
            return var;
        }

        //try to parseExpression as number
        Expr numberExpr = numberExpr(tokens);
        if (numberExpr != null) {
            return numberExpr;
        }

        //Finally, try to parseExpression as ( expr )
        require(tokens, LParen.class);
        Expr expr = expr(tokens);
        require(tokens, RParen.class);
        return expr;

    }

    private NumberExpr numberExpr(List<Token> tokens) {
        NumberToken numberToken = numbertoken(tokens);
        if (numberToken != null) {
            return new NumberExpr(numberToken.getValue());
        }
        OpToken sub = (OpToken)peek(tokens, OpToken.class);
        if (sub == null || sub.getType() != OP.SUB) {
            return null;
        }
        numberToken = (NumberToken)peek(tokens.subList(1, tokens.size()), NumberToken.class);
        if (numberToken == null) {
            return null;
        }
        tokens.remove(0);
        tokens.remove(0);
        return new NumberExpr(-numberToken.getValue());
    }

    private Var varExpr(List<Token> tokens)  {
        Identifier identifierToken = identifier(tokens);
        if (identifierToken == null) {
            return null;
        }
        return vf.getVar(identifierToken.getName());
    }

    private void end(List<Token> ts) throws ParseException{
        require(ts, EndToken.class);
    }

    private Identifier identifier(List<Token> ts)  {
        return (Identifier)expecting(ts, Identifier.class);
    }

    private NumberToken numbertoken(List<Token> ts)  {
        return (NumberToken)expecting(ts, NumberToken.class);
    }

}
