package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.annotations.storage.Link;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.architecture.MgApplication;


public class ApplicationContext extends Context {
    @Optional @Link
    private MgApplication application;

    public ApplicationContext(Context outerContext) {
        super(outerContext);
    }

    public MgApplication getApplication() {
        return application;
    }

    public void setApplication(MgApplication application) {
        this.application = application;
    }

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
    }
}
