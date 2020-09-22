package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;


public class MgLogicalMemberAccessCallExpression extends MgLogicalCallExpression {
    @Mandatory @Part
    private final MgLogicalCallExpression left;

    @Mandatory @Part
    private final MgLogicalCallExpression right;

    public MgLogicalMemberAccessCallExpression(MgLogicalCallExpression left, MgLogicalCallExpression right) {
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
