package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.MgObject;


public interface MgFunctionInstance extends MgObject, MgStructuredInstance {
    @Override
    MgFunction getType();
}
