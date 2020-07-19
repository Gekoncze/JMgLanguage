package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.MgRuntimeEntity;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgObject extends MgRuntimeEntity {
    public static final MgType TYPE = new MgType("Object");

    @Link
    private final MgType type;

    public MgObject(MgType type) {
        this.type = type;
    }

    public MgType getType() {
        return type;
    }
}
