package cz.mg.language.entities.mg.unresolved.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.unresolved.parts.expressions.calls.MgUnresolvedCallExpression;


public class MgUnresolvedLunaryOperatorCallExpression extends MgUnresolvedUnaryOperatorCallExpression {
    @Mandatory @Part
    private final MgUnresolvedCallExpression right;

    public MgUnresolvedLunaryOperatorCallExpression(ReadableText name, MgUnresolvedCallExpression right) {
        super(name);
        this.right = right;
    }

    public MgUnresolvedCallExpression getRight() {
        return right;
    }
}
