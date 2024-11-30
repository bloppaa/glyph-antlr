package expression;

public class Not extends Expression {
    Expression expr;

    public Not(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "!" + expr.toString();
    }
}
