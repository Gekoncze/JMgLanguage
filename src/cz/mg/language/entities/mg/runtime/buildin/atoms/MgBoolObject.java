package cz.mg.language.entities.mg.runtime.buildin.atoms;

import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.buildin.types.MgBoolType;


public class MgBoolObject extends MgAtom {
    @Value
    private boolean value;

    public MgBoolObject(boolean value) {
        super(MgBoolType.getInstance());
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
