package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.entities.mg.runtime.roles.MgStructure;


public interface MgClassInstance extends MgObject, MgInstance, MgStructure {
    @Override
    MgClass getType();
}
