package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public abstract class Operator {
    @Link
    private final MgLogicalExpression expression;

    @Value
    private final int priority;

    public Operator(MgLogicalExpression expression, int priority) {
        this.expression = expression;
        this.priority = priority;
    }

    public MgLogicalExpression getExpression() {
        return expression;
    }

    public int getPriority() {
        return priority;
    }
}
