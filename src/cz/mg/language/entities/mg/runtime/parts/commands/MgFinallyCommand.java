package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public class MgFinallyCommand extends MgCommand {
    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgFinallyCommand(List<MgCommand> commands) {
        this.commands = commands;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        for(MgCommand command : commands){
            command.run(functionInstance);
        }
    }
}
