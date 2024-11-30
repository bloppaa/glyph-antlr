package expression;

import java.util.List;

public class Params extends Expression {
    List<String> paramIds;
    List<String> paramTypes;

    public Params(List<String> paramIds, List<String> paramTypes) {
        this.paramIds = paramIds;
        this.paramTypes = paramTypes;
    }
}
