package cz.mg.language.entities.mg.runtime.roles;

import cz.mg.language.entities.mg.runtime.components.MgFunction;


public interface MgFunctionInstance extends MgObject, MgInstance, MgStructure {
    @Override
    MgFunction getType();
}
