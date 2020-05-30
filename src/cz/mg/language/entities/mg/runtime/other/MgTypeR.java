package cz.mg.language.entities.mg.runtime.other;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.MgClassR;


public class MgTypeR extends MgOtherR {
    @Link
    private final MgClassR clazz;

    @Value
    private final MgModifierR modifier;

    public MgTypeR(MgClassR clazz, MgModifierR modifier) {
        this.clazz = clazz;
        this.modifier = modifier;
    }

    public MgClassR getClazz() {
        return clazz;
    }

    public MgModifierR getModifier() {
        return modifier;
    }
}
