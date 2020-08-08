package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class BinaryOperator extends Operator {
    @Part
    private Operator left;

    @Part
    private Operator right;

    public BinaryOperator(MgLogicalExpression expression, int priority) {
        super(expression, priority);
    }

    public Operator getLeft() {
        return left;
    }

    public void setLeft(Operator left) {
        this.left = left;
    }

    public Operator getRight() {
        return right;
    }

    public void setRight(Operator right) {
        this.right = right;
    }

    @Override
    public boolean isResolved() {
        return left != null && right != null;
    }
}
