package cz.mg.language.entities.mg.runtime.roles;

import cz.mg.collections.list.ArrayList;
import cz.mg.language.entities.mg.runtime.components.MgStamp;


public interface MgComponent extends MgNamedObject {
    ArrayList<MgStamp> getStamps();
}
