package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.instructions.MgInstructionR;
import cz.mg.language.entities.mg.runtime.other.MgOperatorR;


public class MgFunctionR extends MgComponentR {
    @Value
    private final ReadableText name;

    @Part
    private final List<MgVariableR> input = new List<>();

    @Part
    private final List<MgVariableR> output = new List<>();

    @Part
    private final List<MgVariableR> variables = new List<>();

    @Part
    private final List<MgLogicalCommand> commands = new List<>();

    @Part
    private final List<MgInstructionR> instructions = new List<>();

    @Part
    private final MgOperatorR operator;

    public MgFunctionR(ReadableText name, MgOperatorR operator) {
        this.name = name;
        this.operator = operator;
    }

    public MgFunctionR(ReadableText name) {
        this.name = name;
        this.operator = null;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgVariableR> getInput() {
        return input;
    }

    public List<MgVariableR> getOutput() {
        return output;
    }

    public List<MgVariableR> getVariables() {
        return variables;
    }

    public List<MgLogicalCommand> getCommands() {
        return commands;
    }

    public List<MgInstructionR> getInstructions() {
        return instructions;
    }

    public MgOperatorR getOperator() {
        return operator;
    }
}
