package cz.mg.language.entities.c.logical.parts.expressions.values;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Value;


public class CName extends CValue {
    @Value
    private final ReadableText name;

    public CName(ReadableText name) {
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }
}
