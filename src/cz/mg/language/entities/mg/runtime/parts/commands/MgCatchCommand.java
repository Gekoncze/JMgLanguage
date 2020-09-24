package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgCatchCommand extends MgCommand {
    @Mandatory @Link
    private final MgVariable variable;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgCatchCommand(MgVariable variable, List<MgCommand> commands) {
        this.variable = variable;
        this.commands = commands;
    }

    public MgVariable getVariable() {
        return variable;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgCommand command : commands){
            command.run(functionObject);
        }
    }
}
