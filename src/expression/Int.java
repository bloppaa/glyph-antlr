package expression;

public class Int extends Expression {
    public int value;

    public Int(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
