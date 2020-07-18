package cz.mg.language.tasks.mg.builder.part.group;

import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.PathChain;


public abstract class MgBuildPathTask extends MgBuildGroupTask {
    public MgBuildPathTask(Part part, Class<? extends Part> expectedPartType) {
        super(part, expectedPartType, PathChain.class);
    }
}
