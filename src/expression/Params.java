package expression;

import java.util.Map;

public class Params extends Expression {
    Map<String, String> params;

    public Params(Map<String, String> params) {
        this.params = params;
    }
}
