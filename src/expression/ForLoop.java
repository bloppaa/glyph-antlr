package expression;

public class ForLoop extends Expression {
    public String id;
    public Expression start;
    public Expression end;
    public Expression step;
    public Expression block;

    public ForLoop(String id, Expression start, Expression end, Expression step, Expression block) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.step = step;
        this.block = block;
    }

    @Override
    public String toString() {
        return "for (" + id + " = " + start + "; " + id + " < " + end + "; " + id + " += " + step + ") {\n" + block
                + "\n}";
    }
}
