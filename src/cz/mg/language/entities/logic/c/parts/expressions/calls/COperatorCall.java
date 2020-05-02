package cz.mg.language.entities.logic.c.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class COperatorCall extends CCall {
    @Value
    private final ReadableText operator;

    public COperatorCall(ReadableText operator) {
        this.operator = operator;
    }

    public ReadableText getOperator() {
        return operator;
    }
}
