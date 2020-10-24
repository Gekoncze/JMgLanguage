package cz.mg.language.entities.mg.runtime.instances.buildin;

import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgIntType;


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
