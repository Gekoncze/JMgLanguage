package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalLessAbstractExpression;


public class RunaryOperator extends Operator {
    @Part
    private Operator left;

    public RunaryOperator(MgLogicalLessAbstractExpression expression, int priority) {
        super(expression, priority);
    }

    public Operator getLeft() {
        return left;
    }

    public void setLeft(Operator left) {
        this.left = left;
    }

    @Override
    public boolean isResolved() {
        return left != null;
    }
}
