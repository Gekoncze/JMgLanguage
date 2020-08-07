package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Name;


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
        name = get(Name.class, 0).getText();
    }
}
