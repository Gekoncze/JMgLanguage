package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.builder.MgBuildTask;


public abstract class MgBuildPartTask extends MgBuildTask {
    @Input
    private final List<Part> parts;

    public MgBuildPartTask(List<Part> parts) {
        this.parts = parts;
    }

    public List<Part> getParts() {
        return parts;
    }

    @Override
    protected void onRun() {
        buildParts(parts);
    }

    protected abstract void buildParts(List<Part> parts);

    protected void checkCount(int i){
        if(parts.count() > i){
            throw new LanguageException("Too many parts. Expected " + i + ".");
        }

        if(parts.count() < i){
            throw new LanguageException("Not enough parts. Expected " + i + ".");
        }
    }

    protected void checkNotEmpty(){
        if(parts.count() <= 0){
            throw new LanguageException("Missing part.");
        }
    }


    protected <P extends Part> @Mandatory P get(Class<P> clazz, int i){
        Part part = parts.get(i);
        if(part == null) throw new LanguageException("Missing part.");
        if(clazz.isInstance(part)){
            return (P) part;
        } else {
            throw new LanguageException("Expected " + clazz.getSimpleName() + ", but got " + part.getClass().getSimpleName() + ".");
        }
    }
}
