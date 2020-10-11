package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;


public interface MgStructuredInstance extends MgInstance {
    @Override
    MgStructuredType getType();

    Array<MgObject> getObjects();
}
