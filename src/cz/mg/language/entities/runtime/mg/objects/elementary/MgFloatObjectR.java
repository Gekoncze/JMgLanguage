package cz.mg.language.entities.runtime.mg.objects.elementary;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.objects.MgObjectR;


public class MgFloatObjectR extends MgObjectR {
    @Value
    private float value;

    public MgFloatObjectR(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
