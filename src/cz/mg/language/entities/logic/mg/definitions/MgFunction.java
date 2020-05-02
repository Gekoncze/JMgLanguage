package cz.mg.language.entities.logic.mg.definitions;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgLocable;
import cz.mg.language.entities.logic.mg.commands.MgCommand;
import cz.mg.language.entities.logic.mg.instructions.MgInstruction;
import cz.mg.language.entities.logic.mg.other.MgOperator;


public class MgFunction extends MgDefinition implements MgLocable {
    @Value
    private final ReadableText name;

    @Part
    private final List<MgVariable> input = new List<>();

    @Part
    private final List<MgVariable> output = new List<>();

    @Part
    private final List<MgVariable> variables = new List<>();

    @Part
    private final List<MgCommand> commands = new List<>();

    @Part
    private final List<MgInstruction> instructions = new List<>();

    @Part
    private final MgOperator operator;

    public MgFunction(ReadableText name, MgOperator operator) {
        this.name = name;
        this.operator = operator;
    }

    public MgFunction(ReadableText name) {
        this.name = name;
        this.operator = null;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgVariable> getInput() {
        return input;
    }

    public List<MgVariable> getOutput() {
        return output;
    }

    public List<MgVariable> getVariables() {
        return variables;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    public List<MgInstruction> getInstructions() {
        return instructions;
    }

    public MgOperator getOperator() {
        return operator;
    }
}
