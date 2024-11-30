package expression;

import java.util.ArrayList;
import java.util.List;

public class FunctionCall extends Expression {
    String id;
    List<Expression> args = new ArrayList<>();

    public FunctionCall(String id) {
        this.id = id;
    }

    public void setArgs(List<Expression> args) {
        this.args = args;
    }
}
