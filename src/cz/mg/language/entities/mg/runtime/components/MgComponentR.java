package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.List;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.MgRuntimeEntity;


public abstract class MgComponentR extends MgRuntimeEntity implements Named {
    @Link
    private final List<MgStampR> stamps = new List<>();

    public MgComponentR() {
    }

    public List<MgStampR> getStamps() {
        return stamps;
    }
}
