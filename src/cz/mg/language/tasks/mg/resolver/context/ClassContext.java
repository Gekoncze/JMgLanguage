package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;


public class ClassContext extends ComponentContext {
    @Optional @Link
    private MgClass clazz;

    public ClassContext(@Optional Context outerContext) {
        super(outerContext);
    }

    @Override
    public MgComponent getComponent() {
        return clazz;
    }

    public MgClass getClazz() {
        return clazz;
    }

    public void setClazz(MgClass clazz) {
        this.clazz = clazz;
    }

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
        for(MgComponent component : clazz.getVariables()){
            visitor.onVisitComponent(component, null);
        }

        for(MgComponent component : clazz.getFunctions()){
            visitor.onVisitComponent(component, null);
        }
    }
}
