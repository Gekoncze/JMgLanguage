package cz.mg.language.entities.mg.logical.parts.expressions.operators;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class MgLogicalBinaryOperatorExpression extends MgLogicalOperatorExpression {
    @Part
    private MgLogicalExpression left;

    @Part
    private MgLogicalExpression middle;

    @Part
    private MgLogicalExpression right;

    public MgLogicalBinaryOperatorExpression() {
    }

    public MgLogicalBinaryOperatorExpression(MgLogicalExpression left, MgLogicalExpression middle, MgLogicalExpression right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public MgLogicalExpression getLeft() {
        return left;
    }

    public void setLeft(MgLogicalExpression left) {
        this.left = left;
    }

    public MgLogicalExpression getMiddle() {
        return middle;
    }

    public void setMiddle(MgLogicalExpression middle) {
        this.middle = middle;
    }

    public MgLogicalExpression getRight() {
        return right;
    }

    public void setRight(MgLogicalExpression right) {
        this.right = right;
    }
}
