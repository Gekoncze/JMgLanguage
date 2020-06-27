package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.types.MgClass;


public class MgClassObject extends MgObject {
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
        int count = clazz.getVariables().count();
        for(MgClass base : clazz.getClasses()){
            count += base.getVariables().count();
        }
        return new Array<>(count);
    }
}
