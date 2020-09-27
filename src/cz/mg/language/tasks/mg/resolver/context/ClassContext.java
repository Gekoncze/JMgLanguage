package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.MgVariable;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgClass;


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
    public void forEachComponent(ObjectVisitor visitor) {
        for(MgVariable variable : clazz.getVariables()){
            visitor.onVisitComponent(variable, null);
        }

        for(MgFunction function : clazz.getFunctions()){
            visitor.onVisitComponent(function, null);
        }
    }
}
