package cz.mg.language.tasks.mg.resolver;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;


public abstract class Context {
    @Part
    private final Context outerContext;

    public Context(Context outerContext) {
        this.outerContext = outerContext;
    }

    public Context getOuterContext() {
        return outerContext;
    }

    public MgLocation getLocation(){
        if(outerContext != null){
            return outerContext.getLocation();
        } else {
            return null;
        }
    }

    public abstract Iterable<? extends MgComponent> read();
}
