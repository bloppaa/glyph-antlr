package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {
	List<Expression> list;
	public Map<String, Object> values;
	public Map<String, String> types;
	public Map<String, Function> funcs;

	public Map<String, String> textTypes = Map.of(
			Keyword.INT_TYPE, "int",
			Keyword.FLOAT_TYPE, "float",
			Keyword.STRING_TYPE, "string",
			Keyword.BOOL_TYPE, "bool");

	public ExpressionProcessor(List<Expression> list) {
		this.list = list;
		values = new HashMap<>();
		types = new HashMap<>();
		funcs = new HashMap<>();
	}

	private void addDeclaration(Expression e) {
		VariableDeclaration decl = (VariableDeclaration) e;

		if (values.containsKey(decl.id)) {
			String error = String.format("variable '%s' already declared", decl.id);
			throw new Error(error);
		}

		Object result = getEvalResult(decl.expr, this.values);

		if (!checkTypes(result, decl.type)) {
			String textType = textTypes.get(decl.type);
			String error = String.format("cannot assign '%s' to variable '%s' of type '%s'", result, decl.id, textType);
			throw new Error(error);
		}

		if (result instanceof Double) {
			boolean isInt = ((double) result) % 1 == 0;

			if (decl.type.equals(Keyword.INT_TYPE) && !isInt) {
				throw new IllegalArgumentException("cannot assign float to an int variable");
			}
		}

		values.put(decl.id, result);
		types.put(decl.id, decl.type);
	}

	private void addAssignment(Expression e) {
		Assignment assign = (Assignment) e;

		if (!values.containsKey(assign.id)) {
			String error = String.format("variable '%s' not declared", assign.id);
			throw new Error(error);
		}

		Object result = getEvalResult(assign.expr, this.values);
		String type = types.get(assign.id);

		if (!checkTypes(result, type)) {
			String textType = textTypes.get(type);
			String error = String.format("cannot assign '%s' to variable '%s' of type '%s'", result, assign.id,
					textType);
			throw new Error(error);
		}

		if (result instanceof Double) {
			boolean isInt = ((double) result) % 1 == 0;

			if (types.get(assign.id).equals(Keyword.INT_TYPE) && !isInt) {
				throw new IllegalArgumentException(String.format(
						"cannot assign float '%s' to int variable '%s'", result, assign.id));
			}
		}

		values.put(assign.id, result);
	}

	public void proccessExpressions() {
		for (int i = 0; i < list.size(); i++) {
			Expression e = list.get(i);
			if (e instanceof VariableDeclaration) {
				addDeclaration(e);
			} else if (e instanceof Assignment) {
				addAssignment(e);
			} else if (e instanceof Conditional) {
				processConditional((Conditional) e, this.values);
			} else if (e instanceof Print) {
				Expression expr = ((Print) e).expr;
				Object result = getEvalResult(expr, this.values);

				System.out.println(result.toString());
			} else if (e instanceof ForLoop) {
				processForLoop((ForLoop) e, this.values);
			} else if (e instanceof WhileLoop) {
				processWhileLoop((WhileLoop) e, this.values);
			} else if (e instanceof Function) {
				processFunction((Function) e);
			}
		}
	}

	private Object getEvalResult(Expression e, Map<String, Object> values) {
		Object result = null;

		if (e instanceof Int) {
			Int num = (Int) e;
			result = num.value;
		} else if (e instanceof Real) {
			Real num = (Real) e;
			result = num.value;
		} else if (e instanceof Str) {
			Str str = (Str) e;
			result = str.value;
		} else if (e instanceof Variable) {
			Variable var = (Variable) e;

			if (!values.containsKey(var.id)) {
				String error = String.format("variable '%s' not declared", var.id);
				throw new Error(error);
			}

			result = values.get(var.id);
		} else if (e instanceof Parens) {
			Parens parens = (Parens) e;
			result = getEvalResult(parens.expr, values);
		} else if (e instanceof AddSub) {
			AddSub add = (AddSub) e;
			Object left = getEvalResult(add.left, values);
			Object right = getEvalResult(add.right, values);
			String operator = add.operator;

			if (left instanceof Double && right instanceof Double) {
				switch (operator) {
					case Keyword.PLUS:
						result = (double) left + (double) right;
						break;
					case Keyword.MINUS:
						result = (double) left - (double) right;
						break;
				}
			} else if (left instanceof Integer && right instanceof Integer) {
				switch (operator) {
					case Keyword.PLUS:
						result = (int) left + (int) right;
						break;
					case Keyword.MINUS:
						result = (int) left - (int) right;
						break;
				}
			} else if (operator.equals(Keyword.PLUS) && ((left instanceof String && right instanceof String) ||
					(left instanceof Double && right instanceof String) ||
					(left instanceof String && right instanceof Double))) {
				result = left.toString() + right.toString();
			} else if (operator.equals(Keyword.PLUS) && ((left instanceof String && right instanceof String) ||
					(left instanceof Integer && right instanceof String) ||
					(left instanceof String && right instanceof Integer))) {
				result = left.toString() + right.toString();
			} else {
				String error = String.format("cannot apply '%s' to non-numbers", operator);
				throw new Error(error);
			}

		} else if (e instanceof MultDivMod) {
			MultDivMod mult = (MultDivMod) e;
			Object left = getEvalResult(mult.left, values);
			Object right = getEvalResult(mult.right, values);
			String operator = mult.operator;

			if (left instanceof Double && right instanceof Double) {
				switch (operator) {
					case Keyword.TIMES:
						result = (double) left * (double) right;
						break;
					case Keyword.DIVIDE:
						if ((double) right == 0) {
							throw new IllegalArgumentException("division by zero");
						}
						result = (double) left / (double) right;
						break;
					case Keyword.MOD:
						result = (double) left % (double) right;
						break;
				}
			} else if (left instanceof Integer && right instanceof Integer) {
				switch (operator) {
					case Keyword.TIMES:
						result = (int) left * (int) right;
						break;
					case Keyword.DIVIDE:
						if ((int) right == 0) {
							throw new IllegalArgumentException("division by zero");
						}
						result = (int) left / (int) right;
						break;
					case Keyword.MOD:
						result = (int) left % (int) right;
						break;
				}
			} else {
				String error = String.format("cannot apply '%s' to non-numbers", operator);
				throw new Error(error);
			}
			;

		} else if (e instanceof UnaryMinus) {
			UnaryMinus unary = (UnaryMinus) e;
			Object expr = getEvalResult(unary.expr, values);

			if (expr instanceof Double) {
				result = -((double) expr);
			} else if (expr instanceof Integer) {
				result = -((int) expr);
			} else {
				String error = String.format("cannot apply '-' to non-number");
				throw new Error(error);
			}
		} else if (e instanceof Bool) {
			Bool bool = (Bool) e;
			result = bool.value;

		} else if (e instanceof And) {
			And and = (And) e;
			Object left = getEvalResult(and.left, values);
			Object right = getEvalResult(and.right, values);

			if (left instanceof Boolean && right instanceof Boolean) {
				result = (boolean) left && (boolean) right;
			} else {
				String error = String.format("cannot apply '&&' to non-boolean");
				throw new Error(error);
			}
		} else if (e instanceof Or) {
			Or or = (Or) e;
			Object left = getEvalResult(or.left, values);
			Object right = getEvalResult(or.right, values);

			if (left instanceof Boolean && right instanceof Boolean) {
				result = (boolean) left || (boolean) right;
			} else {
				String error = String.format("cannot apply '||' to non-boolean");
				throw new Error(error);
			}
		} else if (e instanceof Not) {
			Not not = (Not) e;
			Object expr = getEvalResult(not.expr, values);

			if (expr instanceof Boolean) {
				result = !((boolean) expr);
			} else {
				String error = String.format("cannot apply '!' to non-boolean");
				throw new Error(error);
			}
		} else if (e instanceof Equality) {
			Equality eq = (Equality) e;
			Object left = getEvalResult(eq.left, values);
			Object right = getEvalResult(eq.right, values);
			String operator = eq.operator;

			switch (operator) {
				case Keyword.EQ:
					result = left.equals(right);
					break;
				case Keyword.NEQ:
					result = !left.equals(right);
					break;
			}
		} else if (e instanceof Comparison) {
			Comparison comp = (Comparison) e;
			Object left = getEvalResult(comp.left, values);
			Object right = getEvalResult(comp.right, values);
			String operator = comp.operator;

			if (left instanceof Double && right instanceof Double) {
				switch (operator) {
					case Keyword.LT:
						result = (double) left < (double) right;
						break;
					case Keyword.GT:
						result = (double) left > (double) right;
						break;
					case Keyword.LTE:
						result = (double) left <= (double) right;
						break;
					case Keyword.GTE:
						result = (double) left >= (double) right;
						break;
				}
			} else if (left instanceof Integer && right instanceof Integer) {
				switch (operator) {
					case Keyword.LT:
						result = (int) left < (int) right;
						break;
					case Keyword.GT:
						result = (int) left > (int) right;
						break;
					case Keyword.LTE:
						result = (int) left <= (int) right;
						break;
					case Keyword.GTE:
						result = (int) left >= (int) right;
						break;
				}
			} else {
				String error = String.format("cannot apply '%s' to non-numbers", operator);
				throw new Error(error);
			}
		} else if (e instanceof FunctionCall) {
			FunctionCall call = (FunctionCall) e;
			result = processFunctionCall(call);
		}

		return result;
	}

	private boolean checkTypes(Object obj, String type) {
		if (obj instanceof Double && type.equals(Keyword.FLOAT_TYPE)) {
			return true;
		} else if (obj instanceof Integer && type.equals(Keyword.INT_TYPE)) {
			return true;
		} else if (obj instanceof String && type.equals(Keyword.STRING_TYPE)) {
			return true;
		} else if (obj instanceof Boolean && type.equals(Keyword.BOOL_TYPE)) {
			return true;
		}

		return false;
	}

	private Object processFunctionCall(FunctionCall call) {
		String id = call.id;

		if (!funcs.containsKey(id)) {
			String error = String.format("Error: function '%s' not declared", id);
			throw new Error(error);
		}

		Function function = (Function) funcs.get(id);
		List<Expression> args = call.args;

		if (args.size() != function.paramIds.size()) {
			String error = String.format("Error: wrong number of arguments for function '%s'", id);
			throw new Error(error);
		}

		for (int i = 0; i < args.size(); i++) {
			Object result = getEvalResult(args.get(i), this.values);
			String paramType = function.paramTypes.get(i);
			String paramId = function.paramIds.get(i);

			boolean areSameType = checkTypes(result, paramType);

			if (!areSameType) {
				String error = String.format("Error: wrong type for argument '%s' of function '%s'", paramId, id);
				throw new Error(error);
			}

			function.values.put(paramId, result);
		}

		Object result = processFunctionBlock((Block) function.block, function);
		function.values.clear();

		return result;
	}

	private Object processFunctionBlock(Block block, Function function) {
		for (Expression e : block.getStatements()) {
			if (e instanceof VariableDeclaration) {
				VariableDeclaration decl = (VariableDeclaration) e;

				if (function.values.containsKey(decl.id)) {
					String error = String.format("Error: variable '%s' already declared", decl.id);
					throw new Error(error);
				}

				Object result = getEvalResult(decl.expr, function.values);

				if (result instanceof Double) {
					boolean isInt = ((double) result) % 1 == 0;

					if (decl.type.equals(Keyword.INT_TYPE) && !isInt) {
						String error = String.format("Error: cannot assign float to an int variable");
						throw new Error(error);
					}
				}

				function.values.put(decl.id, result);

			} else if (e instanceof Assignment) {
				Assignment assign = (Assignment) e;

				if (!function.values.containsKey(assign.id)) {
					String error = String.format("Error: variable '%s' not declared", assign.id);
					throw new Error(error);
				}

				Object result = getEvalResult(assign.expr, function.values);

				if (result instanceof Double) {
					boolean isInt = ((double) result) % 1 == 0;

					int index = function.paramIds.indexOf(assign.id);
					if (function.paramTypes.get(index).equals(Keyword.INT_TYPE) && !isInt) {
						String error = String.format("Error: cannot assign float to an int variable");
						throw new Error(error);
					}
				}

				function.values.put(assign.id, result);
			} else if (e instanceof Print) {
				Expression expr = ((Print) e).expr;
				Object result = getEvalResult(expr, function.values);

				System.out.println(result.toString());
			} else if (e instanceof Conditional) {
				Object result = processFunctionConditional((Conditional) e, function.values, function);

				if (result != null) {
					return result;
				}
			} else if (e instanceof ForLoop) {
				Object result = processFunctionForLoop((ForLoop) e, function.values, function);

				if (result != null) {
					return result;
				}
			} else if (e instanceof WhileLoop) {
				processFunctionWhileLoop((WhileLoop) e, function.values, function);
			} else if (e instanceof Return) {
				Return ret = (Return) e;
				Expression expr = ret.expr;
				Object result = getEvalResult(expr, function.values);

				return result;
			}
		}

		// No return statement was found
		return null;
	}

	private void processBlock(Block block, Map<String, Object> values) {
		for (Expression e : block.getStatements()) {
			if (e instanceof Assignment) {
				addAssignment(e);
			} else if (e instanceof VariableDeclaration) {
				VariableDeclaration decl = (VariableDeclaration) e;
				if (values.containsKey(decl.id)) {
					Assignment assign = new Assignment(decl.id, decl.expr);
					addAssignment(assign);
				} else {
					addDeclaration(e);
				}
			} else if (e instanceof Print) {
				Expression expr = ((Print) e).expr;
				Object result = getEvalResult(expr, values);

				System.out.println(result.toString());
			} else if (e instanceof Conditional) {
				processConditional((Conditional) e, values);
			} else if (e instanceof ForLoop) {
				processForLoop((ForLoop) e, values);
			} else if (e instanceof WhileLoop) {
				processWhileLoop((WhileLoop) e, values);
			}
		}
	}

	private Object processFunctionConditional(Conditional cond, Map<String, Object> values, Function function) {
		boolean condition = (boolean) getEvalResult(cond.condition, values);

		if (condition) {
			Block ifBlock = (Block) cond.ifBlock;
			Object result = processFunctionBlock(ifBlock, function);

			if (result != null) {
				return result;
			}
		} else if (cond.elseBlock != null) {
			Block elseBlock = (Block) cond.elseBlock;
			Object result = processFunctionBlock(elseBlock, function);

			if (result != null) {
				return result;
			}
		}

		return null;
	}

	private void processConditional(Conditional cond, Map<String, Object> values) {
		boolean condition = (boolean) getEvalResult(cond.condition, values);

		if (condition) {
			Block ifBlock = (Block) cond.ifBlock;
			processBlock(ifBlock, values);
		} else if (cond.elseBlock != null) {
			Block elseBlock = (Block) cond.elseBlock;
			processBlock(elseBlock, values);
		}
	}

	private Object processFunctionWhileLoop(WhileLoop whileLoop, Map<String, Object> values, Function function) {
		boolean condition = (boolean) getEvalResult(whileLoop.condition, values);

		while (condition) {
			Block block = (Block) whileLoop.block;
			Object result = processFunctionBlock(block, function);

			if (result != null) {
				return result;
			}

			condition = (boolean) getEvalResult(whileLoop.condition, values);
		}

		return null;
	}

	private void processWhileLoop(WhileLoop whileLoop, Map<String, Object> values) {
		boolean condition = (boolean) getEvalResult(whileLoop.condition, values);

		while (condition) {
			Block block = (Block) whileLoop.block;
			processBlock(block, values);

			condition = (boolean) getEvalResult(whileLoop.condition, values);
		}
	}

	private Object processFunctionForLoop(ForLoop forLoop, Map<String, Object> values, Function function) {
		String id = forLoop.id;

		if (values.containsKey(id)) {
			String error = String.format("Error: variable '%s' already declared", id);
			throw new Error(error);
		}

		int start = (int) getEvalResult(forLoop.start, values);

		int end = (int) getEvalResult(forLoop.end, values);

		// TODO: Check if end is less than start, or if step is negative

		Block block = (Block) forLoop.block;
		int step = (int) getEvalResult(forLoop.step, values);

		for (int i = start; i <= end; i += step) {
			values.put(id, i);
			Object result = processFunctionBlock(block, function);
			values.remove(id);

			if (result != null) {
				return result;
			}
		}

		return null;
	}

	private void processForLoop(ForLoop forLoop, Map<String, Object> values) {
		String id = forLoop.id;

		if (values.containsKey(id)) {
			String error = String.format("Error: variable '%s' already declared", id);
			throw new Error(error);
		}

		int start = (int) getEvalResult(forLoop.start, values);

		int end = (int) getEvalResult(forLoop.end, values);

		// TODO: Check if end is less than start, or if step is negative

		Block block = (Block) forLoop.block;
		int step = (int) getEvalResult(forLoop.step, values);

		for (int i = start; i <= end; i += step) {
			values.put(id, i);
			processBlock(block, values);
			values.remove(id);
		}
	}

	private void processFunction(Function function) {
		String id = function.id;

		if (values.containsKey(id)) {
			String error = String.format("Error: function '%s' already declared", id);
			throw new Error(error);
		}

		funcs.put(id, function);
	}
}
