package cz.mg.language.entities.runtime.mg.components;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.runtime.mg.MgEntityR;
import cz.mg.language.entities.runtime.mg.MgNamedR;


public abstract class MgComponentR extends MgEntityR implements MgNamedR {
    @Link
    private final List<MgStampR> stamps = new List<>();

    public MgComponentR() {
    }

    public List<MgStampR> getStamps() {
        return stamps;
    }
}
