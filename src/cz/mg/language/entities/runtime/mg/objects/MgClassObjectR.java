package cz.mg.language.entities.runtime.mg.objects;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.components.MgClassR;


public class MgClassObjectR extends MgObjectR {
    @Link
    private final MgClassR clazz;

    @Value
    private final Array<MgObjectR> objects;

    public MgClassObjectR(MgClassR clazz) {
        this.clazz = clazz;
        this.objects = new Array<>(clazz.getVariables().count());
    }

    public MgClassR getClazz() {
        return clazz;
    }

    public Array<MgObjectR> getObjects() {
        return objects;
    }
}
