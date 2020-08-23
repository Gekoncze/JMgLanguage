package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;


public class MgLogicalCatchCommand extends MgLogicalBlockCommand {
    @Optional @Part
    private MgLogicalVariable variable;

    public MgLogicalCatchCommand() {
    }

    public MgLogicalVariable getVariable() {
        return variable;
    }

    public void setVariable(MgLogicalVariable variable) {
        this.variable = variable;
    }
}
