package cz.mg.compiler.entities.logic.mg.objects.elementary;

import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.objects.MgObject;


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
