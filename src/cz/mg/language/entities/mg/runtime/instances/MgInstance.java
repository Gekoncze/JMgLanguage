package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.MgObject;


public interface MgInstance extends MgObject {
    MgType getType();
}
