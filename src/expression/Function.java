package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Function extends Expression {
    public String id;
    public Expression block;
    public List<String> paramIds = new ArrayList<>();
    public List<String> paramTypes = new ArrayList<>();
    public Map<String, Object> values = new HashMap<>();

    public Function(String id, Expression block) {
        this.id = id;
        this.block = block;
    }

    public void setParams(List<String> paramIds, List<String> paramTypes) {
        this.paramIds = paramIds;
        this.paramTypes = paramTypes;
    }
}
