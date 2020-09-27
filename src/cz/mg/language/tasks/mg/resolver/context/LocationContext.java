package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgLocation;


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
    public void forEachComponent(ObjectVisitor visitor) {
    }
}
