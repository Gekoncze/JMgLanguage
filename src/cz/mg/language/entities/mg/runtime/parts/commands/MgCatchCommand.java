package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public class MgCatchCommand extends MgCommand {
    @Mandatory @Link
    private final MgInstanceVariable input;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgCatchCommand(@Link MgInstanceVariable input, @Part List<MgCommand> commands) {
        this.input = input;
        this.commands = commands;
    }

    public MgInstanceVariable getInput() {
        return input;
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
