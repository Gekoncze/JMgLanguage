package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.tasks.mg.resolver.Context;


public class LocationContext extends Context {
    @Optional @Link
    private MgLocation location;

    public LocationContext(Context outerContext) {
        super(outerContext);
    }

    public MgLocation getLocation() {
        return location;
    }

    public void setLocation(MgLocation location) {
        this.location = location;
    }

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
    }
}
