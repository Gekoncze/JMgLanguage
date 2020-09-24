package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgSwitchCommand extends MgCommand {
    @Mandatory @Part
    private final List<MgCaseCommand> caseCommands;

    @Mandatory @Value
    private final int caseInput;

    public MgSwitchCommand(List<MgCaseCommand> caseCommands, int caseInput) {
        this.caseCommands = caseCommands;
        this.caseInput = caseInput;
    }

    public List<MgCaseCommand> getCaseCommands() {
        return caseCommands;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgCaseCommand caseCommand : caseCommands){
            if(caseCommand.getExpression() != null){
                caseCommand.getExpression().run(functionObject);
                MgBoolObject condition = (MgBoolObject) functionObject.getObjects().get(caseInput);
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
