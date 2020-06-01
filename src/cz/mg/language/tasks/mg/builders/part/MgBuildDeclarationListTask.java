package cz.mg.language.tasks.mg.builders.part;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.parts.Part;


public class MgBuildDeclarationListTask extends MgBuildPartTask {
    @Output
    private final List<MgLogicalVariable> variables = new List<>();

    public MgBuildDeclarationListTask(Part part) {
        super(part);
    }

    public List<MgLogicalVariable> getVariables() {
        return variables;
    }

    @Override
    protected void onRun() {
        todo;
    }
}
