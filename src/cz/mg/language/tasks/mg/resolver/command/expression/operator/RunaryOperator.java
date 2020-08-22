package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalOperatorExpression;


public class RunaryOperator extends Operator {
    public RunaryOperator(MgLogicalOperatorExpression expression, int priority) {
        super(expression, priority);
    }
}
