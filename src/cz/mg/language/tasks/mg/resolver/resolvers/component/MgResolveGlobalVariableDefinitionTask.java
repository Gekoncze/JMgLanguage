package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.contexts.ComponentContext;
import cz.mg.language.tasks.mg.resolver.contexts.GlobalVariableContext;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveVariableDatatypeTask;


public class MgResolveGlobalVariableDefinitionTask extends MgResolveComponentDefinitionTask {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgGlobalVariable variable;

    public MgResolveGlobalVariableDefinitionTask(Context context, MgLogicalVariable logicalVariable) {
        super(new GlobalVariableContext(context), logicalVariable);
        this.logicalVariable = logicalVariable;
    }

    @Override
    protected GlobalVariableContext getContext() {
        return (GlobalVariableContext) super.getContext();
    }

    public MgGlobalVariable getVariable() {
        return variable;
    }

    @Override
    protected void onResolveComponent() {
        variable = new MgGlobalVariable(logicalVariable.getName());
        getContext().setGlobalVariable(variable);

        postpone(MgResolveVariableDatatypeTask.class, () -> {
            MgResolveVariableDatatypeTask task = new MgResolveVariableDatatypeTask(getContext(), logicalVariable.getDatatype());
            task.run();
            variable.setDatatype(task.getDatatype());
        });

        // todo - resolve object ?
    }
}
