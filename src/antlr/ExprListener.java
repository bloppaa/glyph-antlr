// Generated from Expr.g4 by ANTLR 4.13.2

package antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Program}
	 * labeled alternative in {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ExprParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ExprParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Declaration}
	 * labeled alternative in {@link ExprParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ExprParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Declaration}
	 * labeled alternative in {@link ExprParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ExprParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ExprParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ExprParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link ExprParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(ExprParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link ExprParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(ExprParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Condition}
	 * labeled alternative in {@link ExprParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ExprParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Condition}
	 * labeled alternative in {@link ExprParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ExprParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Function}
	 * labeled alternative in {@link ExprParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunction(ExprParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link ExprParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunction(ExprParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parameters}
	 * labeled alternative in {@link ExprParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParameters(ExprParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parameters}
	 * labeled alternative in {@link ExprParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParameters(ExprParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Arguments}
	 * labeled alternative in {@link ExprParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArguments(ExprParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Arguments}
	 * labeled alternative in {@link ExprParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArguments(ExprParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(ExprParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(ExprParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(ExprParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(ExprParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(ExprParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(ExprParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#return}.
	 * @param ctx the parse tree
	 */
	void enterReturn(ExprParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#return}.
	 * @param ctx the parse tree
	 */
	void exitReturn(ExprParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ExprParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ExprParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ExprParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ExprParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ExprParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ExprParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Or}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOr(ExprParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOr(ExprParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(ExprParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(ExprParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(ExprParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(ExprParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryMinus}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinus(ExprParser.UnaryMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryMinus}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinus(ExprParser.UnaryMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(ExprParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(ExprParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(ExprParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(ExprParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Not}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(ExprParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(ExprParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterComparison(ExprParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitComparison(ExprParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDivMod}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultDivMod(ExprParser.MultDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDivMod}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultDivMod(ExprParser.MultDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code And}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAnd(ExprParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code And}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAnd(ExprParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Real}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterReal(ExprParser.RealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Real}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitReal(ExprParser.RealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(ExprParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(ExprParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEquality(ExprParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEquality(ExprParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(ExprParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(ExprParser.BooleanContext ctx);
}