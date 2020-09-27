package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;


public class GlobalVariableContext extends ComponentContext {
    @Optional @Link
    private MgGlobalVariable globalVariable;

    public GlobalVariableContext(Context outerContext) {
        super(outerContext);
    }

    @Override
    public MgComponent getComponent() {
        return globalVariable;
    }

    public MgGlobalVariable getGlobalVariable() {
        return globalVariable;
    }

    public void setGlobalVariable(MgGlobalVariable globalVariable) {
        this.globalVariable = globalVariable;
    }
}
