package cz.mg.language.tasks.mg.resolver.main.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgClassVariable;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.main.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.main.link.MgResolveVariableDatatypeTask;


public class MgResolveMemberVariableDefinitionTask extends MgResolveTask {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgClassVariable variable;

    public MgResolveMemberVariableDefinitionTask(Context context, MgLogicalVariable logicalVariable) {
        super(context);
        this.logicalVariable = logicalVariable;
    }

    public MgClassVariable getVariable() {
        return variable;
    }

    @Override
    protected void onRun() {
        this.variable = new MgClassVariable(logicalVariable.getName());

        postpone(MgResolveVariableDatatypeTask.class, () -> {
            MgResolveVariableDatatypeTask task = new MgResolveVariableDatatypeTask(getContext(), logicalVariable.getDatatype());
            task.run();
            variable.setDatatype(task.getDatatype());
        });
    }
}
