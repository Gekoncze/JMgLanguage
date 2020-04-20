package cz.mg.compiler.entities.logic.c.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.Named;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.annotations.entity.Value;


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
