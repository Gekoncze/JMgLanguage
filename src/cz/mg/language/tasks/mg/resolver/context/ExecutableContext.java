package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;


public abstract class ExecutableContext extends Context {
    @Mandatory @Part
    private final List<@Mandatory @Link MgInstanceVariable> declaredVariables = new List<>();

    public ExecutableContext(@Optional Context outerContext) {
        super(outerContext);
    }

    public List<MgInstanceVariable> getDeclaredVariables(){
        return declaredVariables;
    }
}
