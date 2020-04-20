package cz.mg.compiler.entities.logic.mg.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Value;


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
