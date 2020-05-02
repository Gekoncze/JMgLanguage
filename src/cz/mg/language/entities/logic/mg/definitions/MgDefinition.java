package cz.mg.language.entities.logic.mg.definitions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgEntity;
import cz.mg.language.entities.logic.mg.MgNamed;
import cz.mg.language.entities.logic.mg.definitions.context.MgContext;


public abstract class MgDefinition extends MgEntity implements MgNamed {
    @Value
    private final List<MgStamp> stamps = new List<>();

    @Link
    private MgContext context = null;

    public List<MgStamp> getStamps() {
        return stamps;
    }

    public MgContext getContext() {
        return context;
    }

    public void setContext(MgContext context) {
        this.context = context;
    }
}
