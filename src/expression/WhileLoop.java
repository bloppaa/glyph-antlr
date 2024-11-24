package expression;

public class WhileLoop extends Expression {
    public Expression condition;
    public Expression block;

    public WhileLoop(Expression condition, Expression block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public String toString() {
        return "while (" + condition + ") {\n" + block + "\n}";
    }
}
