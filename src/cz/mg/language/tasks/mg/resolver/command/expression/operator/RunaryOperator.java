package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class RunaryOperator extends Operator {
    public RunaryOperator(MgLogicalExpression expression, int priority) {
        super(expression, priority);
    }
}
