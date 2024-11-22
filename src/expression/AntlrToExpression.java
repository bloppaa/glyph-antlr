package expression;

import org.antlr.v4.runtime.Token;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.AddSubContext;
import antlr.ExprParser.AndContext;
import antlr.ExprParser.AssignmentContext;
import antlr.ExprParser.BlockContext;
import antlr.ExprParser.BooleanContext;
import antlr.ExprParser.ComparisonContext;
import antlr.ExprParser.ConditionContext;
import antlr.ExprParser.DeclarationContext;
import antlr.ExprParser.EqualityContext;
import antlr.ExprParser.MultDivModContext;
import antlr.ExprParser.NotContext;
import antlr.ExprParser.NumberContext;
import antlr.ExprParser.OrContext;
import antlr.ExprParser.ParensContext;
import antlr.ExprParser.StatementContext;
import antlr.ExprParser.StringContext;
import antlr.ExprParser.UnaryMinusContext;
import antlr.ExprParser.VariableContext;

public class AntlrToExpression extends ExprBaseVisitor<Expression> {

	@Override
	public Expression visitDeclaration(DeclarationContext ctx) {
		Token idToken = ctx.ID().getSymbol();
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;

		String id = ctx.ID().getText();
		String type = ctx.getChild(0).getText();

		Expression expr = visit(ctx.expr());

		if (type.equals("bool") && expr instanceof Number) {
			Number number = (Number) expr;
			String numberValue = number.isInt ? String.valueOf((int) number.num) : String.valueOf(number.num);
			String error = String.format(
					"Error: cannot assign number '%s' to bool variable (%d:%d)", numberValue, line, column);
			throw new Error(error);
		}
		if (type.equals("int") && expr instanceof Bool) {
			Bool bool = (Bool) expr;
			String boolValue = bool.value ? "true" : "false";
			String error = String.format(
					"Error: cannot assign boolean '%s' to int variable (%d:%d)", boolValue, line, column);
			throw new Error(error);
		}
		if (expr instanceof Number) {
			Number num = (Number) expr;
			if (type.equals("int") && !num.isInt) {
				String value = String.valueOf(num.num);
				String error = String.format(
						"Error: cannot assign float '%s' to int (%d:%d)", value, line, column);
				throw new Error(error);
			}
		}

		return new VariableDeclaration(id, type, expr);
	}

	@Override
	public Expression visitAssignment(AssignmentContext ctx) {
		String id = ctx.ID().getText();
		Token idToken = ctx.ID().getSymbol();
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;

		Expression expr = visit(ctx.expr());
		String type = ctx.getChild(0).getText();

		if (type.equals("bool") && expr instanceof Number) {
			Number number = (Number) expr;
			String numberValue = number.isInt ? String.valueOf((int) number.num) : String.valueOf(number.num);
			String error = String.format(
					"Error: cannot assign number '%s' to bool variable (%d:%d)", numberValue, line, column);
			throw new Error(error);
		}
		if (type.equals("int") && expr instanceof Bool) {
			Bool bool = (Bool) expr;
			String boolValue = bool.value ? "true" : "false";
			String error = String.format(
					"Error: cannot assign boolean '%s' to int variable (%d:%d)", boolValue, line, column);
			throw new Error(error);
		}
		if (expr instanceof Number) {
			Number num = (Number) expr;
			if (type.equals("int") && !num.isInt) {
				String value = String.valueOf(num.num);
				String error = String.format(
						"Error: cannot assign float '%s' to int variable (%d:%d)", value, line, column);
				throw new Error(error);
			}
		}

		return new Assignment(id, expr);
	}

	@Override
	public Expression visitUnaryMinus(UnaryMinusContext ctx) {
		Expression expr = visit(ctx.expr());

		if (expr instanceof UnaryMinus) {
			UnaryMinus innerMinus = (UnaryMinus) expr;
			if (innerMinus.expr instanceof Number || innerMinus.expr instanceof Variable) {
				Token token = ctx.start;
				int line = token.getLine();
				int column = token.getCharPositionInLine() + 1;

				String error = String.format(
						"Error: consecutive unary minus not allowed (%d:%d)", line, column);
				throw new Error(error);
			}
		}

		return new UnaryMinus(expr);
	}

	@Override
	public Expression visitAnd(AndContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		return new And(left, right);
	}

	@Override
	public Expression visitBoolean(BooleanContext ctx) {
		String text = ctx.BOOL().getText();
		boolean value = text.equals("true") ? true : false;
		return new Bool(value);
	}

	@Override
	public Expression visitString(StringContext ctx) {
		String text = ctx.STR().getText();
		String value = text.substring(1, text.length() - 1);
		return new Str(value);
	}

	@Override
	public Expression visitComparison(ComparisonContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		String operator = ctx.getChild(1).getText();
		return new Comparison(left, right, operator);
	}

	@Override
	public Expression visitEquality(EqualityContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		String operator = ctx.getChild(1).getText();
		return new Equality(left, right, operator);
	}

	@Override
	public Expression visitNot(NotContext ctx) {
		Expression expr = visit(ctx.expr());
		return new Not(expr);
	}

	@Override
	public Expression visitOr(OrContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		return new Or(left, right);
	}

	@Override
	public Expression visitMultDivMod(MultDivModContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		String operator = ctx.getChild(1).getText();
		return new MultDivMod(left, right, operator);
	}

	@Override
	public Expression visitAddSub(AddSubContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		String operator = ctx.getChild(1).getText();
		return new AddSub(left, right, operator);
	}

	@Override
	public Expression visitParens(ParensContext ctx) {
		Expression expr = visit(ctx.expr());
		return new Parens(expr);
	}

	@Override
	public Expression visitVariable(VariableContext ctx) {
		String id = ctx.ID().getText();
		return new Variable(id);
	}

	@Override
	public Expression visitNumber(NumberContext ctx) {
		String text = ctx.NUM().getText();
		boolean isInt = !text.contains(".");
		double num = Double.parseDouble(text);
		return new Number(num, isInt);
	}

	@Override
	public Expression visitStatement(StatementContext ctx) {
		if (ctx.decl() != null) {
			return visit(ctx.decl());
		}
		if (ctx.assign() != null) {
			return visit(ctx.assign());
		}
		if (ctx.cond() != null) {
			return visit(ctx.cond());
		}
		if (ctx.expr() != null) {
			return visit(ctx.expr());
		}
		return null;
	}

	@Override
	public Expression visitBlock(BlockContext ctx) {
		Block block = new Block();

		for (int i = 0; i < ctx.getChildCount(); i++) {
			Expression e = visit(ctx.getChild(i));
			block.addStatement(e);
		}

		return block;
	}

	@Override
	public Expression visitCondition(ConditionContext ctx) {
		Expression condition = visit(ctx.expr());
		Expression ifBlock = visit(ctx.block(0));
		Expression elseBlock = null;

		if (ctx.block(1) != null) {
			elseBlock = visit(ctx.block(1));
			return new Conditional(condition, ifBlock, elseBlock);
		}

		return new Conditional(condition, ifBlock, elseBlock);
	}

}
