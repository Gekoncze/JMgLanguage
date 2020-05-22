package cz.mg.language.entities.runtime.mg.objects.elementary;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.objects.MgObjectR;


public class MgIntegerObjectR extends MgObjectR {
    @Value
    private int value;

    public MgIntegerObjectR(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
