package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgCatchCommand extends MgCommand {
    @Mandatory @Value
    private final int variableIndex;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgCatchCommand(int variableIndex, List<MgCommand> commands) {
        this.variableIndex = variableIndex;
        this.commands = commands;
    }

    public int getVariableIndex() {
        return variableIndex;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
