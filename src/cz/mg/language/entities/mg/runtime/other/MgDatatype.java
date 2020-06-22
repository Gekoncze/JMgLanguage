package cz.mg.language.entities.mg.runtime.other;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.types.MgClass;


public class MgDatatype extends MgOther {
    @Link
    private final MgClass clazz;

    @Value
    private final MgModifier modifier;

    public MgDatatype(MgClass clazz, MgModifier modifier) {
        this.clazz = clazz;
        this.modifier = modifier;
    }

    public MgClass getClazz() {
        return clazz;
    }

    public MgModifier getModifier() {
        return modifier;
    }
}
