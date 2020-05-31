package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public abstract class MgLogicalBlockCommand extends MgLogicalCommand {
    @Part
    private final List<MgLogicalCommand> commands = new List<>();

    public MgLogicalBlockCommand() {
    }

    public List<MgLogicalCommand> getCommands() {
        return commands;
    }
}
