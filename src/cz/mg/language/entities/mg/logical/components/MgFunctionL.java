package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.commands.MgCommandL;
import cz.mg.language.entities.mg.runtime.components.MgVariableR;


public class MgFunctionL extends MgComponentL {
    @Part
    private final List<MgVariableL> input = new List<>();

    @Part
    private final List<MgVariableL> output = new List<>();

    @Part
    private final List<MgVariableR> variables = new List<>();

    @Part
    private final List<MgCommandL> commands = new List<>();

    public MgFunctionL(ReadableText name) {
        super(name);
    }

    public List<MgVariableL> getInput() {
        return input;
    }

    public List<MgVariableL> getOutput() {
        return output;
    }

    public List<MgVariableR> getVariables() {
        return variables;
    }

    public List<MgCommandL> getCommands() {
        return commands;
    }
}
