package cz.mg.language.tasks.mg.resolvers.contexts;

import cz.mg.collections.special.CompositeCollection;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolvers.Context;


public class FunctionContext extends Context {
    @Link
    private final MgFunction function;

    public FunctionContext(Context outerContext, MgFunction function) {
        super(outerContext);
        this.function = function;
    }

    @Override
    public Iterable<? extends MgComponent> read() {
        return new CompositeCollection(function.getInput(), function.getOutput(), function.getLocal());
    }
}
