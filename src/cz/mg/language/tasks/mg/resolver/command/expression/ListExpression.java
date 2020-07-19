package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public class ListExpression extends Expression {
    @Part
    private final List<Expression> expressions = new List<>();

    public ListExpression() {
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
