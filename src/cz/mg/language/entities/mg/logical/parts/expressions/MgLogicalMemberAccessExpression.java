package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;


public class MgLogicalMemberAccessExpression extends MgLogicalExpression {
    @Mandatory @Part
    private final MgLogicalExpression left;

    @Mandatory @Part
    private final MgLogicalExpression right;

    public MgLogicalMemberAccessExpression(MgLogicalExpression left, MgLogicalExpression right) {
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
