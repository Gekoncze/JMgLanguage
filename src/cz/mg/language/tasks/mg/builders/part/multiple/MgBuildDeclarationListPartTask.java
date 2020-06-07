package cz.mg.language.tasks.mg.builders.part.multiple;

import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.parts.Declaration;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Group;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;
import cz.mg.language.tasks.mg.builders.part.single.MgBuildDeclarationPartTask;


public class MgBuildDeclarationListPartTask extends MgBuildMultiplePartTask<MgLogicalVariable> {
    public MgBuildDeclarationListPartTask(Part part) {
        super(part);
    }

    @Override
    protected Class<? extends Part> getExpectedPartType() {
        return Declaration.class;
    }

    @Override
    protected Class<? extends Group> getExpectedGroupType() {
        return ListChain.class;
    }

    @Override
    protected MgLogicalVariable build(Part part) {
        return MgBuildDeclarationPartTask.buildStatic(part);
    }
}
