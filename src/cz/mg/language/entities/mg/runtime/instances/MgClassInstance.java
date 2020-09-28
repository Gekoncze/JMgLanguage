package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.entities.mg.runtime.MgObject;


public interface MgClassInstance extends MgObject, MgInstance, MgStructuredInstance {
    @Override
    MgClass getType();
}
