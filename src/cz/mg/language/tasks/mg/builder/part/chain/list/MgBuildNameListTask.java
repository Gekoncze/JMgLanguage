package cz.mg.language.tasks.mg.builder.part.chain.list;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.builder.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builder.part.chain.MgBuildListTask;


public class MgBuildNameListTask extends MgBuildListTask {
    @Output
    private final List<ReadableText> names = new List<>();

    @Subtask
    private final List<MgBuildNameTask> subtasks = new List<>();

    public MgBuildNameListTask(List<Part> parts) {
        super(parts);
    }

    public List<ReadableText> getNames() {
        return names;
    }

    @Override
    protected void buildParts(List<Part> parts) {
        subtasks.addLast(new MgBuildNameTask(parts));
        subtasks.getLast().run();
        names.addLast(subtasks.getLast().getName());
    }
}
