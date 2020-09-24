package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;


public abstract class MgLogicalBlockCommand extends MgLogicalCommand {
    @Value
    private ReadableText name;

    @Part
    private final List<MgLogicalCommand> commands = new List<>();

    public MgLogicalBlockCommand() {
    }

    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }

    public List<MgLogicalCommand> getCommands() {
        return commands;
    }
}
