package cz.mg.compiler.entities.logic.mg.objects;

import cz.mg.collections.array.Array;
import cz.mg.compiler.annotations.entity.Link;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.definitions.MgClass;


public class MgClassObject extends MgObject {
    @Link
    private final MgClass clazz;

    @Value
    private final Array<MgObject> objects;

    public MgClassObject(MgClass clazz) {
        this.clazz = clazz;
        this.objects = new Array<>(clazz.getVariables().count());
    }

    public MgClass getClazz() {
        return clazz;
    }

    public Array<MgObject> getObjects() {
        return objects;
    }
}
