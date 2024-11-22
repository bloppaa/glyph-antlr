package expression;

public class Print extends Expression {
    public Expression expr;

    public Print(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "print(" + expr.toString() + ")";
    }
}
