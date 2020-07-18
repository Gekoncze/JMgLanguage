package cz.mg.language.tasks.mg.builder.part;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.MgBuildTask;


public abstract class MgBuildPartTask<P extends Part> extends MgBuildTask {
    @Input
    protected final Part part;

    @Input
    protected final Class<? extends Part> expectedPartType;

    public MgBuildPartTask(Part part, Class<P> expectedPartType) {
        this.part = part;
        this.expectedPartType = expectedPartType;
    }

    @Override
    protected void onRun() {
        if(expectedPartType.isInstance(part)){
            buildPart((P) part);
        } else {
            throw new LanguageException("Expected " + expectedPartType.getSimpleName() + ", but got " + part.getClass().getSimpleName() + ".");
        }
    }

    protected abstract void buildPart(P part);
}
