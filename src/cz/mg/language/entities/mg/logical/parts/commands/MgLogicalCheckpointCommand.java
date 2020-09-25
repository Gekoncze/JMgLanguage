package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Optional;


public class MgLogicalCheckpointCommand extends MgLogicalCommand {
    @Optional @Part
    private MgLogicalTryCommand tryCommand;

    @Mandatory @Part
    private final List<MgLogicalCatchCommand> catchCommands = new List<>();

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

    public List<MgLogicalCatchCommand> getCatchCommands() {
        return catchCommands;
    }

    public MgLogicalFinallyCommand getFinallyCommand() {
        return finallyCommand;
    }

    public void setFinallyCommand(MgLogicalFinallyCommand finallyCommand) {
        this.finallyCommand = finallyCommand;
    }
}
