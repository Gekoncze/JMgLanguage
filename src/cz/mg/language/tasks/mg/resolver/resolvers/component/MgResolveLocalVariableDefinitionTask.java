package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveVariableDatatypeTask;


public class MgResolveLocalVariableDefinitionTask extends MgResolveComponentDefinitionTask<MgLocalVariable> {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgLocalVariable variable;

    public MgResolveLocalVariableDefinitionTask(Store<MgLocalVariable> store, Context context, MgLogicalVariable logicalVariable) {
        super(store, context, logicalVariable);
        this.logicalVariable = logicalVariable;
    }

    @Override
    public MgLocalVariable getOutput() {
        return variable;
    }

    @Override
    protected MgLocalVariable onResolveComponent() {
        this.variable = new MgLocalVariable(logicalVariable.getName());

        createAndPostpone(
            MgResolveVariableDatatypeTask.class,
            logicalVariable,
            datatype -> variable.setDatatype(datatype)
        );

        return variable;
    }
}
