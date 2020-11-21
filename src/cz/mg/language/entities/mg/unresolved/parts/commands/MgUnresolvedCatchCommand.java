package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.unresolved.components.MgUnresolvedVariable;


public class MgUnresolvedCatchCommand extends MgUnresolvedBlockCommand {
    @Optional @Part
    private MgUnresolvedVariable variable;

    public MgUnresolvedCatchCommand() {
    }

    public MgUnresolvedCatchCommand(MgUnresolvedVariable variable) {
        this.variable = variable;
    }

    public MgUnresolvedVariable getVariable() {
        return variable;
    }

    public void setVariable(MgUnresolvedVariable variable) {
        this.variable = variable;
    }
}
