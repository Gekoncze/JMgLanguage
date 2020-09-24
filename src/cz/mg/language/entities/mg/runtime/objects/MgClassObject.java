package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;


public class MgClassObject extends MgObject<MgClass> {
    @Part
    private final Array<MgObject> objects;

    public MgClassObject(MgClass clazz) {
        super(clazz);
        this.objects = generateArray(clazz);
    }

    public Array<MgObject> getObjects() {
        return objects;
    }

    private static Array<MgObject> generateArray(MgClass clazz){
        int count = 0;
        while(clazz != null){
            count += clazz.getVariables().count();
            clazz = clazz.getBaseClass();
        }
        return new Array<>(count);
    }
}
