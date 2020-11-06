package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public abstract class MgBlockCommand extends MgCommand {
    @Mandatory @Part
    private final List<MgCommand> commands = new List<>();

    public MgBlockCommand() {
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
