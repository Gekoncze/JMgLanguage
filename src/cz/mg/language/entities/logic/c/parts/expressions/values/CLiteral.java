package cz.mg.language.entities.logic.c.parts.expressions.values;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class CLiteral extends CValue {
    @Value
    private final ReadableText value;

    public CLiteral(ReadableText value) {
        this.value = value;
    }

    public ReadableText getValue() {
        return value;
    }
}
