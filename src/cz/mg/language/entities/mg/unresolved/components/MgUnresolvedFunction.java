package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.unresolved.parts.commands.MgUnresolvedCommand;


public class MgUnresolvedFunction extends MgUnresolvedComponent {
    @Mandatory @Part
    private final List<MgUnresolvedVariable> input = new List<>();

    @Mandatory @Part
    private final List<MgUnresolvedVariable> output = new List<>();

    @Mandatory @Part
    private final List<MgUnresolvedCommand> commands = new List<>();

    public MgUnresolvedFunction(ReadableText name) {
        super(name);
    }

    public List<MgUnresolvedVariable> getInput() {
        return input;
    }

    public List<MgUnresolvedVariable> getOutput() {
        return output;
    }

    public List<MgUnresolvedCommand> getCommands() {
        return commands;
    }
}
