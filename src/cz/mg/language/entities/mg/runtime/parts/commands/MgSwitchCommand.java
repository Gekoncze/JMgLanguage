package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgBoolObject;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;


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
    public void run(MgFunctionInstanceImpl functionObject) {
        for(MgCaseCommand caseCommand : caseCommands){
            if(caseCommand.getExpression() != null){
                caseCommand.getExpression().run(functionObject);
                MgBoolObject condition = (MgBoolObject) functionObject.getObjects().get(caseCommand.getInput().getOffset());
                if(condition.getValue()){
                    caseCommand.run(functionObject);
                    return;
                }
            } else {
                caseCommand.run(functionObject);
                return;
            }
        }
    }
}
