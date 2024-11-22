package expression;

public class Or extends Expression {
    Expression left;
    Expression right;

    public Or(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.format("%s || %s", left.toString(), right.toString());
    }
}
