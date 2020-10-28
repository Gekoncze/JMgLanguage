package cz.mg.language.entities.mg.logical.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;


public class MgLogicalFunction extends MgLogicalComponent {
    @Mandatory @Part
    private final List<MgLogicalVariable> input = new List<>();

    @Mandatory @Part
    private final List<MgLogicalVariable> output = new List<>();

    @Mandatory @Part
    private final List<MgLogicalCommand> commands = new List<>();

    public MgLogicalFunction(ReadableText name) {
        super(name);
    }

    public List<MgLogicalVariable> getInput() {
        return input;
    }

    public List<MgLogicalVariable> getOutput() {
        return output;
    }

    public List<MgLogicalCommand> getCommands() {
        return commands;
    }
}
