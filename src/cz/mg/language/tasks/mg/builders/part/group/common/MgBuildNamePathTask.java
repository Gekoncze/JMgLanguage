package cz.mg.language.tasks.mg.builders.part.group.common;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.part.group.MgBuildPathTask;
import cz.mg.language.tasks.mg.builders.pattern.Processor;


public class MgBuildNamePathTask extends MgBuildPathTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildNameTask.class,
        MgBuildNamePathTask.class,
        (source, destination) -> destination.names.addLast(source.getName())
    );

    @Output
    private final List<ReadableText> names = new List<>();

    public MgBuildNamePathTask(Part part) {
        super(part, Name.class);
    }

    public List<ReadableText> getNames() {
        return names;
    }

    @Override
    protected Processor getProcessor() {
        return PROCESSOR;
    }
}
