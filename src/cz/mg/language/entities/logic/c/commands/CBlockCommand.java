package cz.mg.language.entities.logic.c.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public abstract class CBlockCommand extends CCommand {
    @Part
    private final List<CCommand> commands = new List<>();

    public CBlockCommand(CCommand... commands) {
        this.commands.addCollectionLast(commands);
    }

    public List<CCommand> getCommands() {
        return commands;
    }
}
