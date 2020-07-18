package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.array.Array;
import cz.mg.collections.special.CompositeCollection;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolver.Context;


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
        if(clazz == null) return new Array<>();
        return new CompositeCollection(clazz.getVariables(), clazz.getFunctions());
    }
}
