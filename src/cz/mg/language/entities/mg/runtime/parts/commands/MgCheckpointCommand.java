package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgCheckpointCommand extends MgCommand {
    @Mandatory @Part
    private final MgTryCommand tryCommand;

    @Optional @Part
    private final MgCatchCommand catchCommand;

    @Optional @Part
    private final MgFinallyCommand finallyCommand;

    public MgCheckpointCommand(MgTryCommand tryCommand, MgCatchCommand catchCommand, MgFinallyCommand finallyCommand) {
        this.tryCommand = tryCommand;
        this.catchCommand = catchCommand;
        this.finallyCommand = finallyCommand;
    }

    public MgTryCommand getTryCommand() {
        return tryCommand;
    }

    public MgCatchCommand getCatchCommand() {
        return catchCommand;
    }

    public MgFinallyCommand getFinallyCommand() {
        return finallyCommand;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
