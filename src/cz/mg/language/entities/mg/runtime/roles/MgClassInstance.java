package cz.mg.language.entities.mg.runtime.roles;

import cz.mg.language.entities.mg.runtime.components.MgClass;


public interface MgClassInstance extends MgObject, MgInstance, MgStructure {
    @Override
    MgClass getType();
}
