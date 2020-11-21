package cz.mg.language.entities.mg.unresolved.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.unresolved.parts.expressions.calls.MgUnresolvedCallExpression;


public class MgUnresolvedRunaryOperatorCallExpression extends MgUnresolvedUnaryOperatorCallExpression {
    @Mandatory @Part
    private final MgUnresolvedCallExpression left;

    public MgUnresolvedRunaryOperatorCallExpression(ReadableText name, MgUnresolvedCallExpression left) {
        super(name);
        this.left = left;
    }

    public MgUnresolvedCallExpression getLeft() {
        return left;
    }
}
