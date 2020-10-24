package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;


public class MgLogicalCatchCommand extends MgLogicalBlockCommand {
    @Optional @Part
    private MgLogicalVariable variable;

    public MgLogicalCatchCommand() {
    }

    public MgLogicalCatchCommand(MgLogicalVariable variable) {
        this.variable = variable;
    }

    public MgLogicalVariable getVariable() {
        return variable;
    }

    public void setVariable(MgLogicalVariable variable) {
        this.variable = variable;
    }
}
