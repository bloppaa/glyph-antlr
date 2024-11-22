package expression;

import java.util.ArrayList;
import java.util.List;

public class Block extends Expression {
    List<Expression> statements;

    public Block() {
        this.statements = new ArrayList<>();
    }

    public void addStatement(Expression e) {
        statements.add(e);
    }

    public List<Expression> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Expression stmt : statements) {
            sb.append(stmt).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
