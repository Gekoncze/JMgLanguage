package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolver.Context;


public class ClassContext extends Context {
    @Optional @Link
    private MgClass clazz;

    public ClassContext(@Optional Context outerContext) {
        super(outerContext);
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
            visitor.onVisitComponent(component);
        }

        for(MgComponent component : clazz.getFunctions()){
            visitor.onVisitComponent(component);
        }
    }
}
