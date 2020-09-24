package cz.mg.language.entities.c.logical.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;


public class CVariable extends CPart implements Named {
    @Part
    private final CType type;

    @Value
    private final ReadableText name;

    public CVariable(CType type, ReadableText name) {
        this.type = type;
        this.name = name;
    }

    public CType getType() {
        return type;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
