package expression;

public class Str extends Expression {
    public String value;

    public Str(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", value);
    }
}
