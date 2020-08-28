package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgSwitchCommand extends MgCommand {
    @Mandatory @Part
    private final List<MgCaseCommand> caseCommands;

    public MgSwitchCommand(List<MgCaseCommand> caseCommands) {
        this.caseCommands = caseCommands;
    }

    public List<MgCaseCommand> getCaseCommands() {
        return caseCommands;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
