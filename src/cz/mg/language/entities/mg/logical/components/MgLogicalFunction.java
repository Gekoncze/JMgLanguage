package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.clazzes.MgVariable;


public class MgLogicalFunction extends MgLogicalComponent {
    @Part
    private final List<MgLogicalVariable> input = new List<>();

    @Part
    private final List<MgLogicalVariable> output = new List<>();

    @Part
    private final List<MgVariable> variables = new List<>();

    @Part
    private final List<MgLogicalCommand> commands = new List<>();

    public MgLogicalFunction() {
    }

    public MgLogicalFunction(ReadableText name) {
        super(name);
    }

    public List<MgLogicalVariable> getInput() {
        return input;
    }

    public List<MgLogicalVariable> getOutput() {
        return output;
    }

    public List<MgVariable> getVariables() {
        return variables;
    }

    public List<MgLogicalCommand> getCommands() {
        return commands;
    }
}
