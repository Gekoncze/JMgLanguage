package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveVariableDatatypeTask;


public class MgResolveVariableDefinitionTask extends MgResolveComponentDefinitionTask<MgVariable> {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgVariable variable;

    public MgResolveVariableDefinitionTask(Store<MgVariable> store, Context context, MgLogicalVariable logicalVariable) {
        super(store, context, logicalVariable);
        this.logicalVariable = logicalVariable;
    }

    @Override
    public MgVariable getOutput() {
        return variable;
    }

    @Override
    protected MgVariable onResolveComponent() {
        this.variable = new MgVariable(logicalVariable.getName());

        createAndPostpone(
            MgResolveVariableDatatypeTask.class,
            logicalVariable,
            datatype -> variable.setDatatype(datatype)
        );

        return variable;
    }
}
