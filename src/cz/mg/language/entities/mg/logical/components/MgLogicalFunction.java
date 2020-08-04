package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.MgLogicalOperator;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;


public class MgLogicalFunction extends MgLogicalComponent {
    @Part
    private final List<MgLogicalVariable> input = new List<>();

    @Part
    private final List<MgLogicalVariable> output = new List<>();

    @Part
    private final List<MgLogicalCommand> commands = new List<>();

    @Value
    private MgLogicalOperator operator;

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

    public List<MgLogicalCommand> getCommands() {
        return commands;
    }

    public MgLogicalOperator getOperator() {
        return operator;
    }

    public void setOperator(MgLogicalOperator operator) {
        this.operator = operator;
    }
}
