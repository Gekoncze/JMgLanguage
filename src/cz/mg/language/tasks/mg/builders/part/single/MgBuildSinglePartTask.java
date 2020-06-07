package cz.mg.language.tasks.mg.builders.part.single;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildPartTask;


public abstract class MgBuildSinglePartTask<T> extends MgBuildPartTask {
    @Output
    private T output;

    public MgBuildSinglePartTask(Part part) {
        super(part);
    }

    public T getOutput() {
        return output;
    }

    @Override
    protected void onRun() {
        if(getExpectedPartType().isInstance(part)){
            output = build(part);
        } else {
            throw new LanguageException("Expected " + getExpectedPartType().getSimpleName() + ", but got " + part.getClass().getSimpleName() + ".");
        }
    }

    protected abstract Class<? extends Part> getExpectedPartType();
    protected abstract T build(Part part);
}
