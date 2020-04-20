package cz.mg.compiler.entities.logic.mg.definitions;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.entity.Link;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.MgEntity;
import cz.mg.compiler.entities.logic.mg.MgNamed;
import cz.mg.compiler.entities.logic.mg.definitions.context.MgContext;


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
