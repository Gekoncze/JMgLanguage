package cz.mg.language.tasks.mg.resolver;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;


public abstract class Context {
    @Optional @Part
    private final Context outerContext;

    public Context(@Optional Context outerContext) {
        this.outerContext = outerContext;
    }

    public @Optional Context getOuterContext() {
        return outerContext;
    }

    public MgLocation getLocation(){
        if(outerContext != null){
            return outerContext.getLocation();
        } else {
            return null;
        }
    }

    public abstract void forEachComponent(ComponentVisitor visitor);

    public interface ComponentVisitor {
        void onVisitComponent(MgComponent component);
    }
}
