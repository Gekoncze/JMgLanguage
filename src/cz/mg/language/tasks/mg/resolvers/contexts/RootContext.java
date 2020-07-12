package cz.mg.language.tasks.mg.resolvers.contexts;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolvers.Context;


public class RootContext extends Context {
    @Link
    private final List<MgComponent> components;

    public RootContext(List<MgComponent> components) {
        super(null);
        this.components = components;
    }

    @Override
    public Iterable<MgComponent> read() {
        return components;
    }
}
