package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalOperatorExpression;


public class BinaryOperator extends Operator {
    public BinaryOperator(MgLogicalOperatorExpression expression, int priority) {
        super(expression, priority);
    }
}
