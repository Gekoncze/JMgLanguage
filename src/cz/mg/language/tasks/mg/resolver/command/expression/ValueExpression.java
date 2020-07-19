package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class ValueExpression extends Expression {
    @Value
    private ReadableText value;

    public ValueExpression() {
    }

    public ReadableText getValue() {
        return value;
    }

    public void setValue(ReadableText value) {
        this.value = value;
    }
}
