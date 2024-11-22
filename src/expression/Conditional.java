package expression;

public class Conditional extends Expression {
    public Expression condition;
    public Expression ifBlock;

    public Conditional(Expression condition, Expression ifBlock) {
        this.condition = condition;
        this.ifBlock = ifBlock;
    }

    // TODO: toString method
}
