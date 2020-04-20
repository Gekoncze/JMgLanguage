package cz.mg.compiler.entities.logic.c.parts;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.Named;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.annotations.entity.Value;


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
