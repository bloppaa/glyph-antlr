package expression;

public class Number extends Expression {
	public double num;
	public boolean isInt;

	public Number(double num, boolean isInt) {
		this.num = num;
		this.isInt = isInt;
	}

	@Override
	public String toString() {
		return isInt ? String.valueOf((int) num) : String.valueOf(num);
	}
}
