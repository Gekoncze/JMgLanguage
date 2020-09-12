package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveVariableDatatypeTask;


public class MgResolveMemberVariableDefinitionTask extends MgResolveComponentDefinitionTask<MgMemberVariable> {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgMemberVariable variable;

    public MgResolveMemberVariableDefinitionTask(Store<MgMemberVariable> store, Context context, MgLogicalVariable logicalVariable) {
        super(store, context, logicalVariable);
        this.logicalVariable = logicalVariable;
    }

    @Override
    public MgMemberVariable getOutput() {
        return variable;
    }

    @Override
    protected MgMemberVariable onResolveComponent() {
        this.variable = new MgMemberVariable(logicalVariable.getName());

        createAndPostpone(
            MgResolveVariableDatatypeTask.class,
            logicalVariable,
            datatype -> variable.setDatatype(datatype)
        );

        return variable;
    }
}
