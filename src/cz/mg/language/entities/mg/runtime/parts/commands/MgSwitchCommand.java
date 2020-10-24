package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgBoolObject;


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
    public void run(MgFunctionInstance functionInstance) {
        for(MgCaseCommand caseCommand : caseCommands){
            if(caseCommand.getExpression() != null){
                caseCommand.getExpression().run(functionInstance);
                MgBoolObject condition = (MgBoolObject) functionInstance.getObjects().get(caseCommand.getInput().getOffset());
                if(condition.getValue()){
                    caseCommand.run(functionInstance);
                    return;
                }
            } else {
                caseCommand.run(functionInstance);
                return;
            }
        }
    }
}
