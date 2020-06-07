package cz.mg.language.tasks.mg.builders.part.multiple;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Group;
import cz.mg.language.tasks.mg.builders.part.MgBuildPartTask;


public abstract class MgBuildMultiplePartTask<Output> extends MgBuildPartTask {
    @cz.mg.language.annotations.task.Output
    private List<Output> output;

    public MgBuildMultiplePartTask(Part part) {
        super(part);
    }

    public List<Output> getOutput() {
        return output;
    }

    @Override
    protected void onRun() {
        output = new List<>();
        if(getExpectedPartType().isInstance(part)){
            output.addLast(build(part));
        } else if(getExpectedGroupType().isInstance(part)){
            Group group = (Group) part;
            for(Part groupPart : group.getParts()){
                if(getExpectedPartType().isInstance(groupPart)){
                    output.addLast(build(part));
                } else {
                    throw new LanguageException("Expected " + getExpectedPartType().getClass().getSimpleName() + ", but got " + groupPart.getClass().getSimpleName() + ".");
                }
            }
        } else if(part == null){
            throw new LanguageException("Missing " + getExpectedPartType().getClass().getSimpleName() + " or " + getExpectedGroupType().getClass().getSimpleName() + ".");
        } else {
            throw new LanguageException("Expected " + getExpectedPartType().getClass().getSimpleName() + " or " + getExpectedGroupType().getClass().getSimpleName() + ", but got " + part.getClass().getSimpleName() + ".");
        }
    }

    protected abstract Class<? extends Part> getExpectedPartType();
    protected abstract Class<? extends Group> getExpectedGroupType();
    protected abstract Output build(Part part);
}
