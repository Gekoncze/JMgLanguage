package cz.mg.language.entities.mg.runtime.other;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.clazzes.MgClazz;


public class MgDatatype extends MgOther {
    @Link
    private final MgClazz clazz;

    @Value
    private final MgModifier modifier;

    public MgDatatype(MgClazz clazz, MgModifier modifier) {
        this.clazz = clazz;
        this.modifier = modifier;
    }

    public MgClazz getClazz() {
        return clazz;
    }

    public MgModifier getModifier() {
        return modifier;
    }
}
