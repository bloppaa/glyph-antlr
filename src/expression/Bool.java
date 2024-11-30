package expression;

public class Bool extends Expression {
    public boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
