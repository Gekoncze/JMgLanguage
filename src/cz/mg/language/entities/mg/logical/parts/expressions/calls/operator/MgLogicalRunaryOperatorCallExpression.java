package cz.mg.language.entities.mg.logical.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;


public class MgLogicalRunaryOperatorCallExpression extends MgLogicalUnaryOperatorCallExpression {
    @Mandatory @Part
    private final MgLogicalCallExpression left;

    public MgLogicalRunaryOperatorCallExpression(ReadableText name, MgLogicalCallExpression left) {
        super(name);
        this.left = left;
    }

    public MgLogicalCallExpression getLeft() {
        return left;
    }
}
