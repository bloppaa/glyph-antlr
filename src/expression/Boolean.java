package expression;

public class Boolean extends Expression {
    public boolean value;

    public Boolean(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
