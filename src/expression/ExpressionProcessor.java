package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {
	List<Expression> list;
	public Map<String, Object> values;
	public Map<String, String> types;

	public ExpressionProcessor(List<Expression> list) {
		this.list = list;
		values = new HashMap<>();
		types = new HashMap<>();
	}

	private void addDeclaration(Expression e) {
		VariableDeclaration decl = (VariableDeclaration) e;

		if (values.containsKey(decl.id)) {
			String error = String.format("Error: variable '%s' already declared", decl.id);
			throw new Error(error);
		}

		Object result = getEvalResult(decl.expr);

		if (result instanceof Double) {
			boolean isInt = ((double) result) % 1 == 0;

			if (decl.type.equals("int") && !isInt) {
				throw new IllegalArgumentException("Error: cannot assign float to an int variable");
			}
		}

		values.put(decl.id, result);
		types.put(decl.id, decl.type);
	}

	private void addAssignment(Expression e) {
		Assignment assign = (Assignment) e;

		if (!values.containsKey(assign.id)) {
			String error = String.format("Error: variable '%s' not declared", assign.id);
			throw new Error(error);
		}

		Object result = getEvalResult(assign.expr);

		if (result instanceof Double) {
			boolean isInt = ((double) result) % 1 == 0;

			if (types.get(assign.id).equals("int") && !isInt) {
				throw new IllegalArgumentException(String.format(
						"Error: cannot assign float '%s' to int variable '%s'", result, assign.id));
			}
		}

		values.put(assign.id, result);
	}

	public List<String> getEvaluationResults() {
		List<String> evaluations = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			Expression e = list.get(i);
			if (e instanceof VariableDeclaration) {
				addDeclaration(e);
			} else if (e instanceof Assignment) {
				addAssignment(e);
			} else if (e instanceof Conditional) {
				processConditional((Conditional) e);
			} else if (e instanceof Variable) {
				// TODO: debugging purposes. Remove later
				Variable var = (Variable) e;
				if (!values.containsKey(var.id)) {
					String error = String.format(
							"Error: variable '%s' not declared", var.id);
					throw new Error(error);
				}
				String input = e.toString();
				Object result = getEvalResult(e);
				String stringResult;

				if (result instanceof Double) {
					boolean isInt = (double) result % 1 == 0;
					stringResult = isInt ? String.valueOf((int) (double) result) : String.valueOf(result);
				} else {
					stringResult = String.valueOf(result);
				}

				evaluations.add(input + " is " + stringResult);
			}
		}

		return evaluations;
	}

	private Object getEvalResult(Expression e) {
		Object result = null;

		if (e instanceof Number) {
			Number num = (Number) e;
			result = num.num;
		} else if (e instanceof Str) {
			Str str = (Str) e;
			result = str.value;
		} else if (e instanceof Variable) {
			Variable var = (Variable) e;
			result = values.get(var.id);
		} else if (e instanceof Parens) {
			Parens parens = (Parens) e;
			result = getEvalResult(parens.expr);
		} else if (e instanceof AddSub) {
			AddSub add = (AddSub) e;
			Object left = getEvalResult(add.left);
			Object right = getEvalResult(add.right);
			String operator = add.operator;

			if (left instanceof Double && right instanceof Double) {
				switch (operator) {
					case "+":
						result = (double) left + (double) right;
						break;
					case "-":
						result = (double) left - (double) right;
						break;
				}
			} else if (left instanceof String && right instanceof String && operator.equals("+")) {
				result = (String) left + (String) right;
			} else {
				String error = String.format("Error: cannot apply '%s' to non-numbers", operator);
				throw new Error(error);
			}

		} else if (e instanceof MultDivMod) {
			MultDivMod mult = (MultDivMod) e;
			Object left = getEvalResult(mult.left);
			Object right = getEvalResult(mult.right);
			String operator = mult.operator;

			if (left instanceof Double && right instanceof Double) {
				switch (operator) {
					case "*":
						result = (double) left * (double) right;
						break;
					case "/":
						if ((double) right == 0) {
							throw new IllegalArgumentException("Error: division by zero");
						}
						result = (double) left / (double) right;
						break;
					case "%":
						result = (double) left % (double) right;
						break;
				}
			} else {
				String error = String.format("Error: cannot apply '%s' to non-numbers", operator);
				throw new Error(error);
			}
			;

		} else if (e instanceof UnaryMinus) {
			UnaryMinus unary = (UnaryMinus) e;
			Object expr = getEvalResult(unary.expr);

			if (expr instanceof Double) {
				result = -((double) expr);
			} else {
				String error = String.format("Error: cannot apply '-' to non-number");
				throw new Error(error);
			}
		} else if (e instanceof Bool) {
			Bool bool = (Bool) e;
			result = bool.value;

		} else if (e instanceof And) {
			And and = (And) e;
			Object left = getEvalResult(and.left);
			Object right = getEvalResult(and.right);

			if (left instanceof Boolean && right instanceof Boolean) {
				result = (boolean) left && (boolean) right;
			} else {
				String error = String.format("Error: cannot apply '&&' to non-boolean");
				throw new Error(error);
			}
		} else if (e instanceof Or) {
			Or or = (Or) e;
			Object left = getEvalResult(or.left);
			Object right = getEvalResult(or.right);

			if (left instanceof Boolean && right instanceof Boolean) {
				result = (boolean) left || (boolean) right;
			} else {
				String error = String.format("Error: cannot apply '||' to non-boolean");
				throw new Error(error);
			}
		} else if (e instanceof Not) {
			Not not = (Not) e;
			Object expr = getEvalResult(not.expr);

			if (expr instanceof Boolean) {
				result = !((boolean) expr);
			} else {
				String error = String.format("Error: cannot apply '!' to non-boolean");
				throw new Error(error);
			}
		} else if (e instanceof Equality) {
			Equality eq = (Equality) e;
			Object left = getEvalResult(eq.left);
			Object right = getEvalResult(eq.right);
			String operator = eq.operator;

			switch (operator) {
				case "==":
					result = left.equals(right);
					break;
				case "!=":
					result = !left.equals(right);
					break;
			}
		} else if (e instanceof Comparison) {
			Comparison comp = (Comparison) e;
			Object left = getEvalResult(comp.left);
			Object right = getEvalResult(comp.right);
			String operator = comp.operator;

			if (left instanceof Double && right instanceof Double) {
				switch (operator) {
					case "<":
						result = (double) left < (double) right;
						break;
					case ">":
						result = (double) left > (double) right;
						break;
					case "<=":
						result = (double) left <= (double) right;
						break;
					case ">=":
						result = (double) left >= (double) right;
						break;
				}
			} else {
				String error = String.format("Error: cannot apply '%s' to non-numbers", operator);
				throw new Error(error);
			}
		}
		return result;
	}

	private void processBlock(Block block) {
		for (Expression e : block.getStatements()) {
			if (e instanceof Assignment) {
				addAssignment(e);
			} else if (e instanceof VariableDeclaration) {
				addDeclaration(e);
			} else {
				// TODO: debugging purposes. Remove later
				String input = e.toString();
				Object result = getEvalResult(e);
				String stringResult;

				if (result instanceof Double) {
					boolean isInt = (double) result % 1 == 0;
					stringResult = isInt ? String.valueOf((int) (double) result) : String.valueOf(result);
				} else {
					stringResult = String.valueOf(result);
				}

				System.out.println(input + " is " + stringResult);
			}
		}
	}

	private void processConditional(Conditional cond) {
		boolean condition = (boolean) getEvalResult(cond.condition);

		if (condition) {
			Block ifBlock = (Block) cond.ifBlock;
			processBlock(ifBlock);
		} else if (cond.elseBlock != null) {
			Block elseBlock = (Block) cond.elseBlock;
			processBlock(elseBlock);
		}
	}
}
