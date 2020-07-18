package cz.mg.language.tasks.mg.builder.part.group;

import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;


public abstract class MgBuildListTask extends MgBuildGroupTask {
    public MgBuildListTask(Part part, Class<? extends Part> expectedPartType) {
        super(part, expectedPartType, ListChain.class);
    }
}
