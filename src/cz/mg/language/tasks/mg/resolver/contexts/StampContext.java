package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.tasks.mg.resolver.Context;


public class StampContext extends ComponentContext {
    @Optional @Link
    private MgStamp stamp;

    public StampContext(Context outerContext) {
        super(outerContext);
    }

    @Override
    public MgComponent getComponent() {
        return stamp;
    }

    public MgStamp getStamp() {
        return stamp;
    }

    public void setStamp(MgStamp stamp) {
        this.stamp = stamp;
    }
}
