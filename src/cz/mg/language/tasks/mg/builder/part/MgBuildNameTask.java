package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Name;


public class MgBuildNameTask extends MgBuildPartTask<Name> {
    @Output
    private ReadableText name;

    public MgBuildNameTask(Part part) {
        super(part, Name.class);
    }

    public ReadableText getName() {
        return name;
    }

    @Override
    protected void buildPart(Name part) {
        name = part.getText();
    }
}
