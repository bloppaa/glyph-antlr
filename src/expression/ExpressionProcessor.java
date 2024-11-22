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

	public List<String> getEvaluationResults() {
		List<String> evaluations = new ArrayList<String>();

		for (int i = 0; i < list.size(); i += 2) {
			Expression e = list.get(i);
			if (e instanceof VariableDeclaration) {
				VariableDeclaration decl = (VariableDeclaration) e;
				Object result = getEvalResult(decl.expr);

				if (result instanceof Double) {
					boolean isInt = ((double) result) % 1 == 0;

					if (decl.type.equals("int") && !isInt) {
						throw new IllegalArgumentException("Error: cannot assign float to an int variable");
					}
				}

				values.put(decl.id, result);
				types.put(decl.id, decl.type);
			} else if (e instanceof Assignment) {
				Assignment assign = (Assignment) e;
				Object result = getEvalResult(assign.expr);

				if (result instanceof Double) {
					boolean isInt = ((double) result) % 1 == 0;

					if (types.get(assign.id).equals("int") && !isInt) {
						throw new IllegalArgumentException(String.format(
								"Error: cannot assign float '%s' to int variable '%s'", result, assign.id));
					}
				}

				values.put(assign.id, result);
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
		}
		return result;
	}
}
