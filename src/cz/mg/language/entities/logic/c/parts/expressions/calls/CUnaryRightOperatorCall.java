package cz.mg.language.entities.logic.c.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.c.parts.expressions.CExpression;


public class CUnaryRightOperatorCall extends COperatorCall {
    @Part
    private final CExpression right;

    public CUnaryRightOperatorCall(ReadableText operator, CExpression right) {
        super(operator);
        this.right = right;
    }

    public CExpression getRight() {
        return right;
    }
}
