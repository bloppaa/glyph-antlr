package expression;

import java.util.List;

public class Args extends Expression {
    List<Expression> args;

    public Args(List<Expression> args) {
        this.args = args;
    }
}
