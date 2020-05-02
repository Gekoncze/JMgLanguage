package cz.mg.language.entities.logic.mg.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgValue extends MgExpression {
    @Value
    private final ReadableText value;

    public MgValue(ReadableText value) {
        this.value = value;
    }

    public ReadableText getValue() {
        return value;
    }
}
