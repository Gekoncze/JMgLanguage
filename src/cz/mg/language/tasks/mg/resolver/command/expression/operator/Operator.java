package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalLessAbstractExpression;


public abstract class Operator {
    @Link
    private final MgLogicalLessAbstractExpression expression;

    @Value
    private final int priority;

    public Operator(MgLogicalLessAbstractExpression expression, int priority) {
        this.expression = expression;
        this.priority = priority;
    }

    public MgLogicalLessAbstractExpression getExpression() {
        return expression;
    }

    public int getPriority() {
        return priority;
    }

    public abstract boolean isResolved();
}
