package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.entities.text.structured.parts.leaves.Operator;


public class MgBuildNameTask extends MgBuildPartTask {
    @Output
    private ReadableText name;

    public MgBuildNameTask(List<Part> parts) {
        super(parts);
    }

    public ReadableText getName() {
        return name;
    }

    @Override
    protected void buildParts(List<Part> parts) {
        checkCount(1);
        try {
            name = get(Name.class, 0).getText();
        } catch (LanguageException e){
            name = get(Operator.class, 0).getText();
            if(!name.equals("*")){
                throw new LanguageException("Expected name or *, but got operator.");
            }
        }
    }
}
