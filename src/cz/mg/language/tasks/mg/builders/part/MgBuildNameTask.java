package cz.mg.language.tasks.mg.builders.part;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Name;


public class MgBuildNameTask extends MgBuildPartTask {
    @Output
    private ReadableText name = null;

    public MgBuildNameTask(Part part) {
        super(part);
    }

    public ReadableText getName() {
        return name;
    }

    @Override
    protected void onRun() {
        if(part instanceof Name){
            name = ((Name) part).getText();
        } else {
            throw new LanguageException("Expected name, but got " + part.getClass().getSimpleName() + ".");
        }
    }
}
