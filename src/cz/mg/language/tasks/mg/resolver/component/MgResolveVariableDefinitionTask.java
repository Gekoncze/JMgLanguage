package cz.mg.language.tasks.mg.resolver.component;

import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.tasks.mg.resolver.context.component.VariableContext;
import cz.mg.language.tasks.mg.resolver.link.MgResolveVariableDatatypeTask;


public abstract class MgResolveVariableDefinitionTask extends MgResolveComponentDefinitionTask {
    public MgResolveVariableDefinitionTask(VariableContext context, MgLogicalVariable logicalVariable) {
        super(context, logicalVariable);
    }

    @Override
    protected VariableContext getContext() {
        return (VariableContext) super.getContext();
    }

    protected void resolveDatatype(MgLogicalVariable logicalVariable){
        postpone(MgResolveVariableDatatypeTask.class, () -> {
            MgResolveVariableDatatypeTask task = new MgResolveVariableDatatypeTask(getContext(), logicalVariable.getDatatype());
            task.run();
            getContext().getVariable().setDatatype(task.getDatatype());
        });
    }
}
