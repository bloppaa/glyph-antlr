package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {
	List<Expression> list;
	public Map<String, Double> values;
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
				double result = getEvalResult(decl.expr);

				if (decl.type.equals("int") && result % 1 != 0) {
					throw new IllegalArgumentException("Error: cannot assign float to an int variable");
				}

				values.put(decl.id, result);
			} else {
				// TODO: debugging purposes. Remove later
				String input = e.toString();
				double result = getEvalResult(e);
				evaluations.add(input + " is " + result);
			}
		}

		return evaluations;
	}

	private double getEvalResult(Expression e) {
		double result = 0;

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
			double left = getEvalResult(add.left);
			double right = getEvalResult(add.right);
			switch (add.operator) {
				case "+":
					result = left + right;
					break;
				case "-":
					result = left - right;
					break;
			}
		} else if (e instanceof MultDivMod) {
			MultDivMod mult = (MultDivMod) e;
			double left = getEvalResult(mult.left);
			double right = getEvalResult(mult.right);
			switch (mult.operator) {
				case "*":
					result = left * right;
					break;
				case "/":
					result = left / right;
					break;
				case "%":
					result = left % right;
					break;
			}
		} else if (e instanceof UnaryMinus) {
			UnaryMinus unary = (UnaryMinus) e;
			double expr = getEvalResult(unary.expr);
			result = -expr;
		}
		return result;
	}
}
