package cz.mg.language.entities.mg.logical.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;


public class MgLogicalBinaryOperatorCallExpression extends MgLogicalOperatorCallExpression {
    @Part
    private final MgLogicalCallExpression left;

    @Part
    private final MgLogicalCallExpression right;

    public MgLogicalBinaryOperatorCallExpression(ReadableText name, MgLogicalCallExpression left, MgLogicalCallExpression right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    public MgLogicalCallExpression getLeft() {
        return left;
    }

    public MgLogicalCallExpression getRight() {
        return right;
    }
}
