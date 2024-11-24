package expression;

import java.util.HashMap;
import java.util.Map;

public class Function extends Expression {
    String id;
    Expression block;
    Map<String, String> params = new HashMap<>();
    String returnType = "void";

    public Function(String id, Expression block) {
        this.id = id;
        this.block = block;
    }

    public void setParams(Map<String, String> params) {
        this.params.putAll(params);
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id='" + id + '\'' +
                ", block=" + block +
                ", params=" + params +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}
