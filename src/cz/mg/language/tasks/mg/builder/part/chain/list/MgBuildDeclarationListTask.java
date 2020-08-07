package cz.mg.language.tasks.mg.builder.part.chain.list;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.builder.part.MgBuildDeclarationTask;
import cz.mg.language.tasks.mg.builder.part.chain.MgBuildListTask;


public class MgBuildDeclarationListTask extends MgBuildListTask {
    @Output
    private final List<MgLogicalVariable> variables = new List<>();

    @Subtask
    private final List<MgBuildDeclarationTask> subtasks = new List<>();

    public MgBuildDeclarationListTask(List<Part> parts) {
        super(parts);
    }

    public List<MgLogicalVariable> getVariables() {
        return variables;
    }

    @Override
    protected void buildParts(List<Part> parts) {
        subtasks.addLast(new MgBuildDeclarationTask(parts));
        subtasks.getLast().run();
        variables.addLast(subtasks.getLast().getVariable());
    }
}
