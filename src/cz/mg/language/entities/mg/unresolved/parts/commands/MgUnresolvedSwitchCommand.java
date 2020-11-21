package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;


public class MgUnresolvedSwitchCommand extends MgUnresolvedCommand {
    @Mandatory @Part
    private final List<MgUnresolvedCaseCommand> commands = new List<>();

    public MgUnresolvedSwitchCommand() {
    }

    public List<MgUnresolvedCaseCommand> getCommands() {
        return commands;
    }
}
