package cz.mg.language.entities.mg.logical.parts.expressions.operators;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class MgLogicalUnaryOperatorExpression extends MgLogicalOperatorExpression {
    @Part
    private final MgLogicalExpression left;

    @Part
    private final MgLogicalExpression right;

    public MgLogicalUnaryOperatorExpression(MgLogicalExpression left, MgLogicalExpression right) {
        this.left = left;
        this.right = right;
    }

    public MgLogicalExpression getLeft() {
        return left;
    }

    public MgLogicalExpression getRight() {
        return right;
    }
}
