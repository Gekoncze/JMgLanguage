package cz.mg.language.entities.mg.runtime.roles;

import cz.mg.language.entities.mg.runtime.components.MgType;


public interface MgInstance extends MgObject {
    MgType getType();
}
