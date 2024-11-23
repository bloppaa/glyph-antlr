package expression;

public class Real extends Expression {
    public double value;

    public Real(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
