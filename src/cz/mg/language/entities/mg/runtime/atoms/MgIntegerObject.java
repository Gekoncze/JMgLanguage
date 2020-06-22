package cz.mg.language.entities.mg.runtime.atoms;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.types.MgType;


public class MgIntegerObject extends MgAtom {
    private static final MgType TYPE = new MgType("Integer");

    @Value
    private int value;

    public MgIntegerObject(int value) {
        super(TYPE);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
