package cz.mg.compiler.entities.logic.c.parts.expressions.values;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Value;


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
