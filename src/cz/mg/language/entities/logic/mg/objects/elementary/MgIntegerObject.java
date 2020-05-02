package cz.mg.language.entities.logic.mg.objects.elementary;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.objects.MgObject;


public class MgIntegerObject extends MgObject {
    @Value
    private int value;

    public MgIntegerObject(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
