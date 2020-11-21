package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Optional;


public class MgUnresolvedCheckpointCommand extends MgUnresolvedCommand {
    @Optional @Part
    private MgUnresolvedTryCommand tryCommand;

    @Mandatory @Part
    private final List<MgUnresolvedCatchCommand> catchCommands = new List<>();

    @Optional @Part
    private MgUnresolvedFinallyCommand finallyCommand;

    public MgUnresolvedCheckpointCommand() {
    }

    public MgUnresolvedTryCommand getTryCommand() {
        return tryCommand;
    }

    public void setTryCommand(MgUnresolvedTryCommand tryCommand) {
        this.tryCommand = tryCommand;
    }

    public List<MgUnresolvedCatchCommand> getCatchCommands() {
        return catchCommands;
    }

    public MgUnresolvedFinallyCommand getFinallyCommand() {
        return finallyCommand;
    }

    public void setFinallyCommand(MgUnresolvedFinallyCommand finallyCommand) {
        this.finallyCommand = finallyCommand;
    }
}
