package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Optional;


public class MgLogicalCheckpointCommand extends MgLogicalBlockCommand {
    @Optional @Part
    private MgLogicalTryCommand tryCommand;

    @Optional @Part
    private MgLogicalCatchCommand catchCommand;

    @Optional @Part
    private MgLogicalFinallyCommand finallyCommand;

    public MgLogicalCheckpointCommand() {
    }

    public MgLogicalTryCommand getTryCommand() {
        return tryCommand;
    }

    public void setTryCommand(MgLogicalTryCommand tryCommand) {
        this.tryCommand = tryCommand;
    }

    public MgLogicalCatchCommand getCatchCommand() {
        return catchCommand;
    }

    public void setCatchCommand(MgLogicalCatchCommand catchCommand) {
        this.catchCommand = catchCommand;
    }

    public MgLogicalFinallyCommand getFinallyCommand() {
        return finallyCommand;
    }

    public void setFinallyCommand(MgLogicalFinallyCommand finallyCommand) {
        this.finallyCommand = finallyCommand;
    }
}
