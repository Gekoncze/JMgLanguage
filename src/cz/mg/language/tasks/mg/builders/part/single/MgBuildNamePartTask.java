package cz.mg.language.tasks.mg.builders.part.single;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Name;


public class MgBuildNamePartTask extends MgBuildSinglePartTask<ReadableText> {
    public MgBuildNamePartTask(Part part) {
        super(part);
    }

    @Override
    protected Class<? extends Part> getExpectedPartType() {
        return Name.class;
    }

    @Override
    protected ReadableText build(Part part) {
        return buildStatic(part);
    }

    public static ReadableText buildStatic(Part part){
        return ((Name) part).getText();
    }
}
