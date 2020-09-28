package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.entities.mg.runtime.roles.MgStructure;


public interface MgFunctionInstance extends MgObject, MgInstance, MgStructure {
    @Override
    MgFunction getType();
}
