package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgCatchCommand extends MgCommand {
    @Mandatory @Link
    private final MgLocalVariable input;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgCatchCommand(@Link MgLocalVariable input, @Part List<MgCommand> commands) {
        this.input = input;
        this.commands = commands;
    }

    public MgLocalVariable getInput() {
        return input;
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
