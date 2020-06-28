package cz.mg.language.tasks.mg.builders.part;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalVariable;
import cz.mg.language.entities.text.structured.parts.Declaration;
import cz.mg.language.entities.text.structured.parts.Part;


public class MgBuildDeclarationTask extends MgBuildPartTask<Declaration> {
    @Output
    private MgLogicalVariable variable;

    public MgBuildDeclarationTask(Part part) {
        super(part, Declaration.class);
    }

    public MgLogicalVariable getVariable() {
        return variable;
    }

    @Override
    protected void buildPart(Declaration part) {
        variable = new MgLogicalVariable(
            part.getObjectName().getText(),
            part.getTypeName().getText(),
            MgLogicalVariable.Storage.valueOf(part.getStorage().name())
        );
    }
}
