package cz.mg.language.tasks.mg.resolvers.contexts;

import cz.mg.collections.special.CompositeCollection;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolvers.Context;


public class ClassContext extends Context {
    @Link
    private MgClass clazz;

    public ClassContext(Context outerContext) {
        super(outerContext);
    }

    public MgClass getClazz() {
        return clazz;
    }

    public void setClazz(MgClass clazz) {
        this.clazz = clazz;
    }

    @Override
    public Iterable<? extends MgComponent> read() {
        return new CompositeCollection(clazz.getVariables(), clazz.getFunctions());
    }
}
