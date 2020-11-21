package cz.mg.language.entities.mg.unresolved.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.unresolved.parts.expressions.calls.MgUnresolvedCallExpression;


public class MgUnresolvedBinaryOperatorCallExpression extends MgUnresolvedOperatorCallExpression {
    @Mandatory @Part
    private final MgUnresolvedCallExpression left;

    @Mandatory @Part
    private final MgUnresolvedCallExpression right;

    public MgUnresolvedBinaryOperatorCallExpression(
        ReadableText name,
        MgUnresolvedCallExpression left,
        MgUnresolvedCallExpression right
    ) {
        super(name);
        this.left = left;
        this.right = right;
    }

    public MgUnresolvedCallExpression getLeft() {
        return left;
    }

    public MgUnresolvedCallExpression getRight() {
        return right;
    }
}
