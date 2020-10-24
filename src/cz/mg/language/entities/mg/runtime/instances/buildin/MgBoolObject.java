package cz.mg.language.entities.mg.runtime.instances.buildin;

import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgBoolType;


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
