package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.MgRuntimeEntity;
import cz.mg.language.entities.mg.runtime.clazzes.MgClazz;


public abstract class MgObject extends MgRuntimeEntity {
    @Link
    private final MgClazz clazz;

    @Part
    private final Array<Array<MgObject>> objects;

    public MgObject(MgClazz clazz) {
        this.clazz = clazz;
        this.objects = new Array<>(clazz.getClazzes().count() + 1);
        for(int i = 0; i < clazz.getClazzes().count(); i++){
            this.objects.set(new Array<>(clazz.getClazzes().get(i).getVariables().count()), i);
        }
        this.objects.set(new Array<>(clazz.getVariables().count()), clazz.getClazzes().count());
    }

    public MgClazz getClazz() {
        return clazz;
    }
}
