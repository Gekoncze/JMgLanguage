package cz.mg.language.tasks.mg.builders.part.group.common;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalVariable;
import cz.mg.language.entities.text.structured.parts.Declaration;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildDeclarationTask;
import cz.mg.language.tasks.mg.builders.part.group.MgBuildListTask;
import cz.mg.language.tasks.mg.builders.pattern.Processor;


public class MgBuildDeclarationListTask extends MgBuildListTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildDeclarationTask.class,
        MgBuildDeclarationListTask.class,
        (source, destination) -> destination.variables.addLast(source.getVariable())
    );

    @Output
    private final List<MgLogicalVariable> variables = new List<>();

    public MgBuildDeclarationListTask(Part part) {
        super(part, Declaration.class);
    }

    public List<MgLogicalVariable> getVariables() {
        return variables;
    }

    @Override
    protected Processor getProcessor() {
        return PROCESSOR;
    }
}
