package cz.mg.language.entities.mg.runtime.atoms;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgBoolObject extends MgAtom {
    public static final MgType TYPE = new MgType("Bool");

    @Value
    private boolean value;

    public MgBoolObject(boolean value) {
        super(TYPE);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public MgBoolObject copy() {
        return new MgBoolObject(value);
    }
}
