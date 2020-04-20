package cz.mg.compiler.entities.logic.c.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Value;


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
