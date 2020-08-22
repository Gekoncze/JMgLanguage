package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;


public class LeafOperator extends Operator {
    public LeafOperator(MgLogicalCallExpression expression) {
        super(expression, 0);
    }

    @Override
    public boolean isResolved() {
        return true;
    }
}
