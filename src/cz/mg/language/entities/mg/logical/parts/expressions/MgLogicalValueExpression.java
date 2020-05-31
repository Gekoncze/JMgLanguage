package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalValueExpression extends MgLogicalExpression {
    @Value
    private ReadableText value;

    public MgLogicalValueExpression() {
    }

    public MgLogicalValueExpression(ReadableText value) {
        this.value = value;
    }

    public ReadableText getValue() {
        return value;
    }

    public void setValue(ReadableText value) {
        this.value = value;
    }
}
