package cz.mg.language.entities.mg.runtime.objects.buildin;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgFloatObject extends MgObject {
    @Value
    private float value;

    public MgFloatObject(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
