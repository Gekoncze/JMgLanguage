package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.types.MgClass;


public class MgClassObject extends MgObject {
    @Part
    private final ReadableArray<Array<MgObject>> objects;

    public MgClassObject(MgClass clazz) {
        super(clazz);
        this.objects = generateArrays(clazz);
    }

    public ReadableArray<Array<MgObject>> getObjects() {
        return objects;
    }

    private static ReadableArray<Array<MgObject>> generateArrays(MgClass clazz){
        Array<Array<MgObject>> objects = new Array<>(clazz.getClazzes().count() + 1);
        for(int i = 0; i < clazz.getClazzes().count(); i++){
            objects.set(new Array<>(clazz.getClazzes().get(i).getVariables().count()), i);
        }
        objects.set(new Array<>(clazz.getVariables().count()), clazz.getClazzes().count());
        return objects;
    }
}
