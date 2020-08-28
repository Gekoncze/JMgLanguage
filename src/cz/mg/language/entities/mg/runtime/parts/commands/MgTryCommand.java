package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgTryCommand extends MgCommand {
    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgTryCommand(List<MgCommand> commands) {
        this.commands = commands;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
