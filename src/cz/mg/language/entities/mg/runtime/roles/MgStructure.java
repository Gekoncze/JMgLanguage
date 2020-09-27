package cz.mg.language.entities.mg.runtime.roles;

import cz.mg.collections.array.Array;


public interface MgStructure extends MgObject {
    Array<MgObject> getObjects();
}
