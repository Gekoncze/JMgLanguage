package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.tasks.mg.resolver.Context;


public class RootContext extends Context {
    @Link
    private final MgLocation location;

    public RootContext(MgLocation location) {
        super(null);
        this.location = location;
    }

    @Override
    public MgLocation getLocation() {
        return location;
    }

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
    }
}
