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
			switch (add.operator) {
				case "+":
					result = (double) left + (double) right;
					break;
				case "-":
					result = (double) left - (double) right;
					break;
			}
		} else if (e instanceof MultDivMod) {
			MultDivMod mult = (MultDivMod) e;
			Object left = getEvalResult(mult.left);
			Object right = getEvalResult(mult.right);
			switch (mult.operator) {
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
		} else if (e instanceof UnaryMinus) {
			UnaryMinus unary = (UnaryMinus) e;
			Object expr = getEvalResult(unary.expr);
			result = -((double) expr);
		} else if (e instanceof Boolean) {
			Boolean bool = (Boolean) e;
			result = bool.value;

		}
		return result;
	}
}
