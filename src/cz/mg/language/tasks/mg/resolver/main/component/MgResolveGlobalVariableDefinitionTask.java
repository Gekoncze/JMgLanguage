package cz.mg.language.tasks.mg.resolver.main.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.GlobalVariableContext;
import cz.mg.language.tasks.mg.resolver.main.link.MgResolveVariableDatatypeTask;


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
