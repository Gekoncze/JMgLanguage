package cz.mg.language.entities.mg.runtime.instances;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;


public interface MgStructuredInstance extends MgObject {
    Array<MgObject> getObjects();
}
