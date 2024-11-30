package expression;

public class UnaryMinus extends Expression {
    public Expression expr;

    public UnaryMinus(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "-" + expr.toString();
    }
}
