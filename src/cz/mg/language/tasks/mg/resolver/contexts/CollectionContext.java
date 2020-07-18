package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.array.Array;
import cz.mg.collections.special.CompositeCollection;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgCollection;
import cz.mg.language.tasks.mg.resolver.Context;


public class CollectionContext extends Context {
    @Link
    private MgCollection collection;

    public CollectionContext(Context outerContext) {
        super(outerContext);
    }

    public MgCollection getCollection() {
        return collection;
    }

    public void setCollection(MgCollection collection) {
        this.collection = collection;
    }

    @Override
    public Iterable<? extends MgComponent> read() {
        if(collection == null) return new Array<>();
        return new CompositeCollection(collection.getVariables(), collection.getFunctions()/*, collection.getParameters()*/); // TODO
    }
}
