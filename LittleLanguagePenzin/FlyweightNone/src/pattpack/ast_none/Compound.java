package pattpack.ast_none;

import java.io.*;

/**
 *  This class represents a compound statement.
 */

public class Compound extends Statement {
    private Statement [] body;
    /**
     *  Constructor, instances are immutable.
     *  This constructor is for a two-statements compound only.
     *  @param stmt0 The first statement of this compound.
     *  @param stmt1 The second statement of this compound.
     */
    public Compound (Statement stmt0, Statement stmt1) {
	body = new Statement [2];
	body [0] = stmt0;
	body [1] = stmt1;
    }
    /**
     *  Constructor, instances are immutable.
     *  This constructor is for a three-statements compound only.
     *  @param stmt0 The first statement of this compound.
     *  @param stmt1 The second statement of this compound.
     *  @param stmt2 The third statement of this compound.
     */
    public Compound (Statement stmt0, Statement stmt1, Statement stmt2) {
	body = new Statement [3];
	body [0] = stmt0;
	body [1] = stmt1;
	body [2] = stmt2;
    }
    /**
     *  Constructor, instances are immutable.
     *  This constructor is for a four-statements compound only.
     *  @param stmt0 The first statement of this compound.
     *  @param stmt1 The second statement of this compound.
     *  @param stmt2 The third statement of this compound.
     *  @param stmt3 The third statement of this compound.
     */
    public Compound (Statement stmt0, Statement stmt1, Statement stmt2, Statement stmt3) {
	body = new Statement [4];
	body [0] = stmt0;
	body [1] = stmt1;
	body [2] = stmt2;
	body [3] = stmt3;
    }
    /**
     *  Constructor, instances are immutable.
     *  @param body The sequence of statements of this compound.
     */
    public Compound (Statement [] body) { this.body = body; }
    /**
     *  Print this statement with indentation.
     *  @param out The stream where this statement is printed.
     *  @param indent The amount of indentation of this statement.
     */
    public void print (PrintStream out, int indent) {
	super.print (out, indent);
	out.print ("begin");
	for (int i = 0; i < body.length; i++) {
	    body [i].print (out, indent+1);
	    if (i < body.length-1)
		out.print (";");
	}
	super.print (out, indent);
	out.print ("end");
    }
    /**
     * Interpret the statement
     * Needs some value sharing in order to work
     */
    public void interpret() {
        for (int i = 0; i < body.length; i++) {
            body[i].interpret();
        }
    }
}