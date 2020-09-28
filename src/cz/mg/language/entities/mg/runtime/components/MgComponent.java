package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.language.entities.mg.runtime.roles.MgNamedObject;


public interface MgComponent extends MgNamedObject {
    ArrayList<MgStamp> getStamps();
}
