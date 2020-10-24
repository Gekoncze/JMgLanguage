package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.collections.array.Array;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;


public class MgClassInstanceImpl implements MgClassInstance {
    @Mandatory @Link
    private final MgClass type;

    @Mandatory @Part
    private final Array<@Optional MgObject> objects;

    public MgClassInstanceImpl(MgClass type) {
        this.type = type;
        this.objects = new Array<>(type.getVariableCountCache());
    }

    @Override
    public MgClass getType() {
        return type;
    }

    @Override
    public Array<MgObject> getObjects() {
        return objects;
    }
}
