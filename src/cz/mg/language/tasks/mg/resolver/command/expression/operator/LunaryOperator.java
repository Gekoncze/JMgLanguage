package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class LunaryOperator extends Operator {
    public LunaryOperator(MgLogicalExpression expression, int priority) {
        super(expression, priority);
    }
}
