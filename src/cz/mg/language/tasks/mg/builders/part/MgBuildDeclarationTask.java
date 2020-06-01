package cz.mg.language.tasks.mg.builders.part;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.parts.Part;


public class MgBuildDeclarationTask extends MgBuildPartTask {
    @Output
    private final MgLogicalVariable variable = new MgLogicalVariable();

    public MgBuildDeclarationTask(Part part) {
        super(part);
    }

    public MgLogicalVariable getVariable() {
        return variable;
    }

    @Override
    protected void onRun() {
        todo;
    }
}
