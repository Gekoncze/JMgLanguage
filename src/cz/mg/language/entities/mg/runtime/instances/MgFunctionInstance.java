package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.MgObject;


public interface MgFunctionInstance extends MgObject, MgInstance, MgStructuredInstance {
    @Override
    MgFunction getType();
}
