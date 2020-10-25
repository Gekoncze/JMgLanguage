package cz.mg.language.tasks.mg.resolver.component;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.component.VariableContext;


public class MgResolveFunctionVariableDefinitionTask extends MgResolveVariableDefinitionTask {
    @Input
    private final MgFunction function;

    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgInstanceVariable variable;

    public MgResolveFunctionVariableDefinitionTask(Context context, MgFunction function, MgLogicalVariable logicalVariable) {
        super(new VariableContext(context), logicalVariable);
        this.function = function;
        this.logicalVariable = logicalVariable;
    }

    public MgInstanceVariable getVariable() {
        return variable;
    }

    @Override
    protected void onResolveComponent(List<MgStamp> stamps) {
        variable = new MgInstanceVariable(logicalVariable.getName(), function);
        variable.getStamps().addCollectionLast(instanceStampOnly(stamps));
        getContext().setVariable(variable);
        resolveDatatype(logicalVariable);
    }
}
