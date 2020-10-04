package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;


public class MgFunctionInstanceImpl implements MgFunctionInstance {
    @Mandatory @Link
    private final MgFunction type;

    @Mandatory @Part
    private final Array<@Optional MgObject> objects;

    public MgFunctionInstanceImpl(MgFunction type) {
        this.type = type;
        this.objects = new Array<>(type.getVariableCountCache());
    }

    @Override
    public MgFunction getType() {
        return type;
    }

    @Override
    public Array<MgObject> getObjects() {
        return objects;
    }
}
