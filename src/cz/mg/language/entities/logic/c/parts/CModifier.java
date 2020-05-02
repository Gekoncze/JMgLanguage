package cz.mg.language.entities.logic.c.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;


public class CModifier extends CPart implements Named {
    public static CModifier PACKED = new CModifier(new ReadonlyText("__attribute__((packed))"));
    public static CModifier STRUCT = new CModifier(new ReadonlyText("struct"));
    public static CModifier VOLATILE = new CModifier(new ReadonlyText("volatile"));

    @Value
    private final ReadableText name;

    public CModifier(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
