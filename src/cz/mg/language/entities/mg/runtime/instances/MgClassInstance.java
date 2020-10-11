package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;
import cz.mg.language.entities.mg.runtime.MgObject;


public interface MgClassInstance extends MgObject, MgStructuredInstance {
    @Override
    MgClass getType();
}
