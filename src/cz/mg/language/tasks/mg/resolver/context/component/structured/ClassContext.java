package cz.mg.language.tasks.mg.resolver.context.component.structured;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class ClassContext extends StructuredTypeContext {
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
        for(MgVariable variable : clazz.getVariables()){
            visitor.onVisitComponent(variable, null);
        }

        for(MgFunction function : clazz.getFunctions()){
            visitor.onVisitComponent(function, null);
        }
    }
}
