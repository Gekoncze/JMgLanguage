package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.MgComponent;


public abstract class Context {
    @Part
    private final Context outerContext;

    public Context(Context outerContext) {
        this.outerContext = outerContext;
    }

    public Context getOuterContext() {
        return outerContext;
    }

    public abstract Iterable<? extends MgComponent> read();
}
