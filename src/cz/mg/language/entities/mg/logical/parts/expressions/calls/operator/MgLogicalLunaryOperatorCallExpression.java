package cz.mg.language.entities.mg.logical.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;


public class MgLogicalLunaryOperatorCallExpression extends MgLogicalUnaryOperatorCallExpression {
    @Part
    private final MgLogicalCallExpression right;

    public MgLogicalLunaryOperatorCallExpression(ReadableText name, MgLogicalCallExpression right) {
        super(name);
        this.right = right;
    }

    public MgLogicalCallExpression getRight() {
        return right;
    }
}
