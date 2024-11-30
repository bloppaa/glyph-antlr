package expression;

import org.antlr.v4.runtime.tree.ParseTree;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.ProgramContext;

public class AntlrToProgram extends ExprBaseVisitor<Program> {

	@Override
	public Program visitProgram(ProgramContext ctx) {
		Program prog = new Program();

		AntlrToExpression exprVisitor = new AntlrToExpression();

		for (int i = 0; i < ctx.getChildCount() - 1; i++) {
			ParseTree child = ctx.getChild(i);

			if (child.getText().equals(Keyword.SEMICOLON)) {
				continue;
			}

			prog.addExpression(exprVisitor.visit(ctx.getChild(i)));
		}

		return prog;
	}
}
