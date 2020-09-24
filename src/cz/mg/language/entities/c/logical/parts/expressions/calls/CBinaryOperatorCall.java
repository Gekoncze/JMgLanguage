package cz.mg.language.entities.c.logical.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.c.logical.parts.expressions.CExpression;


public class CBinaryOperatorCall extends COperatorCall {
    @Part
    private final CExpression left;

    @Part
    private final CExpression right;

    public CBinaryOperatorCall(ReadableText operator, CExpression left, CExpression right) {
        super(operator);
        this.left = left;
        this.right = right;
    }

    public CExpression getLeft() {
        return left;
    }

    public CExpression getRight() {
        return right;
    }
}
