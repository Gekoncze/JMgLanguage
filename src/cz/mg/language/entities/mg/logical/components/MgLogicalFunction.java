package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;


public class MgLogicalFunction extends MgLogicalComponent {
    @Part
    private final List<MgLogicalVariable> input = new List<>();

    @Part
    private final List<MgLogicalVariable> output = new List<>();

    @Part
    private final List<MgLogicalCommand> commands = new List<>();

    @Value
    private ReadableText operator;

    @Value
    private int priority;

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

    public ReadableText getOperator() {
        return operator;
    }

    public void setOperator(ReadableText operator) {
        this.operator = operator;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
