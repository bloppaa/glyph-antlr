package expression;

public class Return extends Expression {
    Expression expr;

    public Return(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "return " + expr.toString();
    }
}
