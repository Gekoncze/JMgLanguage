package cz.mg.language.entities.c.logical.parts.expressions.values;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Value;


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
