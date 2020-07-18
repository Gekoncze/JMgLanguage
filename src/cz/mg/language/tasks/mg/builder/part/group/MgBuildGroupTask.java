package cz.mg.language.tasks.mg.builder.part.group;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Group;
import cz.mg.language.tasks.mg.builder.part.MgBuildPartTask;
import cz.mg.language.tasks.mg.builder.pattern.Processor;


public abstract class MgBuildGroupTask extends MgBuildPartTask {
    @Input
    private final Class<? extends Group> expectedGroupType;

    @Subtask
    private final List<MgBuildPartTask> subtasks = new List<>();

    public MgBuildGroupTask(Part part, Class<? extends Part> expectedPartType, Class<? extends Group> expectedGroupType) {
        super(part, expectedPartType);
        this.expectedGroupType = expectedGroupType;
    }

    @Override
    protected void onRun() {
        if(expectedPartType.isInstance(part)){
            buildPart(part);
        } else if(expectedGroupType.isInstance(part)){
            Group group = (Group) part;
            for(Part groupPart : group.getParts()){
                buildPart(groupPart);
            }
        } else {
            List<Class> expectations = new List<>();
            if(expectedPartType != null) expectations.addLast(expectedPartType);
            if(expectedGroupType != null) expectations.addLast(expectedGroupType);
            ReadableText expectationsText = expectations.toText(" or ", (expectation) -> new ReadonlyText(expectation.getSimpleName()));
            throw new LanguageException("Expected " + expectationsText + ", but got " + part.getClass().getSimpleName() + ".");
        }
    }

    @Override
    protected void buildPart(Part part){
        MgBuildPartTask subtask = execute(
            create(
                getProcessor().getSourceBuildTaskClass(),
                part
            )
        );
        getProcessor().getSetter().set(subtask, this);
    }

    private MgBuildPartTask create(Class<? extends MgBuildPartTask> clazz, Part part){
        try {
            return clazz.getConstructor(Part.class).newInstance(part);
        } catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }

    private <T extends MgBuildPartTask> T execute(T task){
        subtasks.addLast(task);
        task.run();
        return task;
    }
    
    protected abstract Processor getProcessor();
}
