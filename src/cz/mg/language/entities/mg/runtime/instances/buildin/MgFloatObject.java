package cz.mg.language.entities.mg.runtime.instances.buildin;

import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgFloatType;


public class MgFloatObject extends MgAtom {
    @Value
    private float value;

    public MgFloatObject(float value) {
        super(MgFloatType.getInstance());
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public MgFloatObject copy() {
        return new MgFloatObject(value);
    }
}
