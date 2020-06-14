package cz.mg.language.tasks.mg.builders.part.group.common;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.part.group.MgBuildListTask;
import cz.mg.language.tasks.mg.builders.pattern.Processor;


public class MgBuildNameListTask extends MgBuildListTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildNameTask.class,
        MgBuildNameListTask.class,
        (source, destination) -> destination.names.addLast(source.getName())
    );

    @Output
    private final List<ReadableText> names = new List<>();

    public MgBuildNameListTask(Part part) {
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
