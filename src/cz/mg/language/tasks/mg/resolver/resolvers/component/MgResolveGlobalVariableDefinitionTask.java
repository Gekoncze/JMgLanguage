package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveVariableDatatypeTask;


public class MgResolveGlobalVariableDefinitionTask extends MgResolveComponentDefinitionTask<MgGlobalVariable> {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgGlobalVariable variable;

    public MgResolveGlobalVariableDefinitionTask(Store<MgGlobalVariable> store, Context context, MgLogicalVariable logicalVariable) {
        super(store, context, logicalVariable);
        this.logicalVariable = logicalVariable;
    }

    @Override
    public MgGlobalVariable getOutput() {
        return variable;
    }

    @Override
    protected MgGlobalVariable onResolveComponent() {
        this.variable = new MgGlobalVariable(logicalVariable.getName());

        createAndPostpone(
            MgResolveVariableDatatypeTask.class,
            logicalVariable,
            datatype -> variable.setDatatype(datatype)
        );

        return variable;
    }
}
