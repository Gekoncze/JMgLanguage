package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveVariableDatatypeTask;


public class MgResolveMemberVariableDefinitionTask extends MgResolveTask {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgMemberVariable variable;

    public MgResolveMemberVariableDefinitionTask(Context context, MgLogicalVariable logicalVariable) {
        super(context);
        this.logicalVariable = logicalVariable;
    }

    public MgMemberVariable getVariable() {
        return variable;
    }

    @Override
    protected void onRun() {
        this.variable = new MgMemberVariable(logicalVariable.getName());

        postpone(MgResolveVariableDatatypeTask.class, () -> {
            MgResolveVariableDatatypeTask task = new MgResolveVariableDatatypeTask(getContext(), logicalVariable.getDatatype());
            task.run();
            variable.setDatatype(task.getDatatype());
        });
    }
}
