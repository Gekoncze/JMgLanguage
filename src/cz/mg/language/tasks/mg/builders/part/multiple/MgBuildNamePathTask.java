package cz.mg.language.tasks.mg.builders.part.multiple;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Group;
import cz.mg.language.entities.text.structured.parts.groups.chains.PathChain;
import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.tasks.mg.builders.part.single.MgBuildNamePartTask;


public class MgBuildNamePathTask extends MgBuildMultiplePartTask<ReadableText> {
    public MgBuildNamePathTask(Part part) {
        super(part);
    }

    @Override
    protected Class<? extends Part> getExpectedPartType() {
        return Name.class;
    }

    @Override
    protected Class<? extends Group> getExpectedGroupType() {
        return PathChain.class;
    }

    @Override
    protected ReadableText build(Part part) {
        return MgBuildNamePartTask.buildStatic(part);
    }
}
