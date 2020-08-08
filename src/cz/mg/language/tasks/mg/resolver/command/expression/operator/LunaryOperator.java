package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class LunaryOperator extends Operator {
    @Part
    private Operator right;

    public LunaryOperator(MgLogicalExpression expression, int priority) {
        super(expression, priority);
    }

    public Operator getRight() {
        return right;
    }

    public void setRight(Operator right) {
        this.right = right;
    }

    @Override
    public boolean isResolved() {
        return right != null;
    }
}
