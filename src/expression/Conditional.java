package expression;

public class Conditional extends Expression {
    public Expression condition;
    public Expression ifBlock;
    public Expression elseBlock;

    public Conditional(Expression condition, Expression ifBlock, Expression elseBlock) {
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }
}
