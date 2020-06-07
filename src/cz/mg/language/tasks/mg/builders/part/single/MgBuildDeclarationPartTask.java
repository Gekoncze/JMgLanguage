package cz.mg.language.tasks.mg.builders.part.single;

import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.parts.Declaration;
import cz.mg.language.entities.text.structured.parts.Part;


public class MgBuildDeclarationPartTask extends MgBuildSinglePartTask<MgLogicalVariable> {
    public MgBuildDeclarationPartTask(Part part) {
        super(part);
    }

    @Override
    protected Class<? extends Part> getExpectedPartType() {
        return Declaration.class;
    }

    @Override
    protected MgLogicalVariable build(Part part) {
        return buildStatic(part);
    }

    public static MgLogicalVariable buildStatic(Part part){
        Declaration declaration = (Declaration) part;
        return new MgLogicalVariable(
                declaration.getObjectName().getText(),
                declaration.getTypeName().getText(),
                MgLogicalVariable.Storage.valueOf(declaration.getStorage().name())
        );
    }
}
