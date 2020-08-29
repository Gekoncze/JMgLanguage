package cz.mg.language.entities.mg.runtime.atoms;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgFloatObject extends MgAtom {
    public static final MgType TYPE = new MgType("Float");

    @Value
    private float value;

    public MgFloatObject(float value) {
        super(TYPE);
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
