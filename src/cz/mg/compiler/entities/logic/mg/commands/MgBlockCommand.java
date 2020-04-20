package cz.mg.compiler.entities.logic.mg.commands;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.entity.Part;


public abstract class MgBlockCommand extends MgCommand {
    @Part
    private final List<MgCommand> commands = new List<>();

    public List<MgCommand> getCommands() {
        return commands;
    }
}
