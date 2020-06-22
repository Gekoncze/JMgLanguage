package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.MgRuntimeEntity;
import cz.mg.language.entities.mg.runtime.types.MgType;


public class MgObject extends MgRuntimeEntity {
    @Link
    private final MgType type;

    public MgObject(MgType type) {
        this.type = type;
    }

    public MgType getType() {
        return type;
    }
}
