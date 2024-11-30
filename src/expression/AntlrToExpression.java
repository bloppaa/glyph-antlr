package expression;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.AddSubContext;
import antlr.ExprParser.AndContext;
import antlr.ExprParser.ArgumentsContext;
import antlr.ExprParser.AssignmentContext;
import antlr.ExprParser.BlockContext;
import antlr.ExprParser.BooleanContext;
import antlr.ExprParser.ComparisonContext;
import antlr.ExprParser.ConditionContext;
import antlr.ExprParser.DeclarationContext;
import antlr.ExprParser.EqualityContext;
import antlr.ExprParser.ForLoopContext;
import antlr.ExprParser.FunctionCallContext;
import antlr.ExprParser.FunctionContext;
import antlr.ExprParser.IntContext;
import antlr.ExprParser.MultDivModContext;
import antlr.ExprParser.NotContext;
import antlr.ExprParser.OrContext;
import antlr.ExprParser.ParametersContext;
import antlr.ExprParser.ParensContext;
import antlr.ExprParser.PrintContext;
import antlr.ExprParser.RealContext;
import antlr.ExprParser.ReturnContext;
import antlr.ExprParser.StatementContext;
import antlr.ExprParser.StringContext;
import antlr.ExprParser.UnaryMinusContext;
import antlr.ExprParser.VariableContext;
import antlr.ExprParser.WhileLoopContext;

public class AntlrToExpression extends ExprBaseVisitor<Expression> {

	@Override
	public Expression visitDeclaration(DeclarationContext ctx) {

		String id = ctx.ID().getText();
		String type = ctx.getChild(0).getText();
		Expression expr = visit(ctx.expr());

		return new VariableDeclaration(id, type, expr);
	}

	@Override
	public Expression visitAssignment(AssignmentContext ctx) {
		String id = ctx.ID().getText();
		Expression expr = visit(ctx.expr());

		return new Assignment(id, expr);
	}

	@Override
	public Expression visitUnaryMinus(UnaryMinusContext ctx) {
		Expression expr = visit(ctx.expr());

		if (expr instanceof UnaryMinus) {
			UnaryMinus innerMinus = (UnaryMinus) expr;
			if (innerMinus.expr instanceof Int || innerMinus.expr instanceof Real
					|| innerMinus.expr instanceof Variable) {
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
		boolean value = text.equals(Keyword.TRUE) ? true : false;
		return new Bool(value);
	}

	@Override
	public Expression visitString(StringContext ctx) {
		String text = ctx.STR().getText();

		int delimiterLength = 1;
		if (text.length() > 2 * delimiterLength) {
			int startIndex = text.offsetByCodePoints(0, delimiterLength);
			int endIndex = text.offsetByCodePoints(0, text.codePointCount(0, text.length()) - delimiterLength);

			String value = text.substring(startIndex, endIndex);
			return new Str(value);
		} else {
			throw new IllegalArgumentException("String is too short to contain valid content with delimiters.");
		}
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
	public Expression visitInt(IntContext ctx) {
		String text = ctx.INT().getText();
		int num = Integer.parseInt(text);
		return new Int(num);
	}

	@Override
	public Expression visitReal(RealContext ctx) {
		String text = ctx.FLOAT().getText();
		double num = Double.parseDouble(text);
		return new Real(num);
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
		if (ctx.print() != null) {
			return visit(ctx.print());
		}
		if (ctx.forLoop() != null) {
			return visit(ctx.forLoop());
		}
		if (ctx.whileLoop() != null) {
			return visit(ctx.whileLoop());
		}
		if (ctx.return_() != null) {
			return visit(ctx.return_());
		}
		return null;
	}

	@Override
	public Expression visitPrint(PrintContext ctx) {
		Expression expr = visit(ctx.expr());
		return new Print(expr);
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
	public Expression visitWhileLoop(WhileLoopContext ctx) {
		Expression condition = visit(ctx.expr());
		Expression block = visit(ctx.block());
		return new WhileLoop(condition, block);
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

	@Override
	public Expression visitForLoop(ForLoopContext ctx) {
		String id = ctx.ID().getText();
		Expression start = visit(ctx.expr(0));
		Expression end = visit(ctx.expr(1));
		Expression block = visit(ctx.block());

		if (ctx.expr(2) != null) {
			Expression step = visit(ctx.expr(2));
			return new ForLoop(id, start, end, step, block);
		}
		return new ForLoop(id, start, end, new Int(1), block);
	}

	@Override
	public Expression visitArguments(ArgumentsContext ctx) {
		List<Expression> args = new ArrayList<>();

		for (int i = 0; i < ctx.getChildCount(); i += 2) {
			Expression e = visit(ctx.getChild(i));
			args.add(e);
		}

		return new Args(args);
	}

	@Override
	public Expression visitFunction(FunctionContext ctx) {
		String id = ctx.ID().getText();
		Expression block = visit(ctx.block());
		Function function = new Function(id, block);

		if (ctx.params() != null) {
			Params params = (Params) visit(ctx.params());
			function.setParams(params.paramIds, params.paramTypes);
		}

		return function;
	}

	@Override
	public Expression visitFunctionCall(FunctionCallContext ctx) {
		String id = ctx.ID().getText();
		FunctionCall functionCall = new FunctionCall(id);

		if (ctx.args() != null) {
			Args args = (Args) visit(ctx.args());
			functionCall.setArgs(args.args);
		}

		return functionCall;
	}

	@Override
	public Expression visitParameters(ParametersContext ctx) {
		List<String> paramIds = new ArrayList<>();
		List<String> paramTypes = new ArrayList<>();

		for (int i = 0; i < ctx.getChildCount() / 2; i++) {
			String id = ctx.ID(i).getText();
			String type = ctx.type(i).getText();

			paramIds.add(id);
			paramTypes.add(type);
		}

		return new Params(paramIds, paramTypes);
	}

	@Override
	public Expression visitReturn(ReturnContext ctx) {
		Expression expr = visit(ctx.expr());
		return new Return(expr);
	}
}
