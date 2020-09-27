package cz.mg.language.tasks.mg.resolver.main.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.main.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.main.link.MgResolveVariableDatatypeTask;


public class MgResolveLocalVariableDefinitionTask extends MgResolveTask {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgLocalVariable variable;

    public MgResolveLocalVariableDefinitionTask(Context context, MgLogicalVariable logicalVariable) {
        super(context);
        this.logicalVariable = logicalVariable;
    }

    public MgLocalVariable getVariable() {
        return variable;
    }

    @Override
    protected void onRun() {
        this.variable = new MgLocalVariable(logicalVariable.getName());

        postpone(MgResolveVariableDatatypeTask.class, () -> {
            MgResolveVariableDatatypeTask task = new MgResolveVariableDatatypeTask(getContext(), logicalVariable.getDatatype());
            task.run();
            variable.setDatatype(task.getDatatype());
        });
    }
}
