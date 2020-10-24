package cz.mg.language.tasks.mg.resolver.component;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.component.VariableContext;


public class MgResolveFunctionVariableDefinitionTask extends MgResolveVariableDefinitionTask {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgFunctionVariable variable;

    public MgResolveFunctionVariableDefinitionTask(Context context, MgLogicalVariable logicalVariable) {
        super(new VariableContext(context), logicalVariable);
        this.logicalVariable = logicalVariable;
    }

    public MgFunctionVariable getVariable() {
        return variable;
    }

    @Override
    protected void onResolveComponent(List<MgStamp> stamps) {
        variable = new MgFunctionVariable(logicalVariable.getName());
        variable.getStamps().addCollectionLast(instanceStampOnly(stamps));
        getContext().setVariable(variable);
        resolveDatatype(logicalVariable);
    }
}
