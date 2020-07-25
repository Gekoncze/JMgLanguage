package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.MgRuntimeEntity;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgObject<Type extends MgType> extends MgRuntimeEntity {
    public static final MgType TYPE = new MgType("Object");

    @Link
    private final Type type;

    public MgObject(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
