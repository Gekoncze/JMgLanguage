package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;


public class MgLogicalSwitchCommand extends MgLogicalCommand {
    @Mandatory @Part
    private final List<MgLogicalCaseCommand> commands = new List<>();

    public MgLogicalSwitchCommand() {
    }

    public List<MgLogicalCaseCommand> getCommands() {
        return commands;
    }
}
