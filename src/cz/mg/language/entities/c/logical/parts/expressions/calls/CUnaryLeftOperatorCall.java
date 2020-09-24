package cz.mg.language.entities.c.logical.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.c.logical.parts.expressions.CExpression;


public class CUnaryLeftOperatorCall extends COperatorCall {
    @Part
    private final CExpression left;

    public CUnaryLeftOperatorCall(ReadableText operator, CExpression left) {
        super(operator);
        this.left = left;
    }

    public CExpression getLeft() {
        return left;
    }
}
