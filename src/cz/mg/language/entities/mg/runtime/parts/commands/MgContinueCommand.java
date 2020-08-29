package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgContinueCommand extends MgCommand {
    @Optional @Part
    private final MgCommand command;

    public MgContinueCommand(MgCommand command) {
        this.command = command;
    }

    public MgCommand getCommand() {
        return command;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
