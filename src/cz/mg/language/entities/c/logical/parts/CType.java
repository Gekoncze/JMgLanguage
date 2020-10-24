package cz.mg.language.entities.c.logical.parts;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.Named;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;


public class CType extends CPart implements Named {
    public static CType VOID = new CType(new ReadonlyText("void"));

    @Value
    private final ReadableText name;

    @Part
    private final List<CModifier> modifiers = new List<>();

    @Part
    private final List<CPointer> pointers = new List<>();

    public CType(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<CModifier> getModifiers() {
        return modifiers;
    }

    public List<CPointer> getPointers() {
        return pointers;
    }
}
