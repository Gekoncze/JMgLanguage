package cz.mg.language.entities.mg.runtime.other;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.types.MgType;


public class MgDatatype extends MgOther {
    @Link
    private final MgType type;

    @Value
    private final MgModifier modifier;

    public MgDatatype(MgType type, MgModifier modifier) {
        this.type = type;
        this.modifier = modifier;
    }

    public MgType getType() {
        return type;
    }

    public MgModifier getModifier() {
        return modifier;
    }
}
