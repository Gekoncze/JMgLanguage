package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;


public abstract class MgUnresolvedBlockCommand extends MgUnresolvedCommand {
    @Value
    private ReadableText name;

    @Part
    private final List<MgUnresolvedCommand> commands = new List<>();

    public MgUnresolvedBlockCommand() {
    }

    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }

    public List<MgUnresolvedCommand> getCommands() {
        return commands;
    }
}
