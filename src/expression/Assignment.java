package expression;

public class Assignment extends Expression {
    public String id;
    public Expression expr;

    public Assignment(String id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return id + " = " + expr.toString();
    }
}
