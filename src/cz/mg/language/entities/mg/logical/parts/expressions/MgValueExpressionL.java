package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgValueExpressionL extends MgExpressionL {
    @Value
    private final ReadableText value;

    public MgValueExpressionL(ReadableText value) {
        this.value = value;
    }

    public ReadableText getValue() {
        return value;
    }
}
