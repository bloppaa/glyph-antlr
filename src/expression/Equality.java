package expression;

public class Equality extends Expression {
    Expression left;
    Expression right;
    String operator;

    public Equality(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", left.toString(), operator, right.toString());
    }
}
