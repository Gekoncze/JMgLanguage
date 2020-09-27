package cz.mg.language.entities.mg.runtime.buildin.atoms;

import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.buildin.types.MgIntType;


public class MgIntObject extends MgAtom {
    @Value
    private int value;

    public MgIntObject(int value) {
        super(MgIntType.getInstance());
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public MgIntObject copy() {
        return new MgIntObject(value);
    }
}
