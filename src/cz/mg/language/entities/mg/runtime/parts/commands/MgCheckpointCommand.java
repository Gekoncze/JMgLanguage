package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ArtificialException;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.RollbackException;


public class MgCheckpointCommand extends MgCommand {
    @Mandatory @Part
    private final MgTryCommand tryCommand;

    @Optional @Part
    private final List<MgCatchCommand> catchCommands;

    @Optional @Part
    private final MgFinallyCommand finallyCommand;

    @Mandatory @Value
    private final int output;

    public MgCheckpointCommand(MgTryCommand tryCommand, List<MgCatchCommand> catchCommands, MgFinallyCommand finallyCommand, int output) {
        this.tryCommand = tryCommand;
        this.catchCommands = catchCommands;
        this.finallyCommand = finallyCommand;
        this.output = output;
    }

    public MgTryCommand getTryCommand() {
        return tryCommand;
    }

    public List<MgCatchCommand> getCatchCommands() {
        return catchCommands;
    }

    public MgFinallyCommand getFinallyCommand() {
        return finallyCommand;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        ArtificialException ae = null;
        try {
            tryCommand.run(functionObject);
        } catch (ArtificialException e){
            ae = e;
        }

        MgCatchCommand handler = find(ae, functionObject);
        if(handler != null){
            ae = null;
            try {
                handler.run(functionObject);
            } catch (ArtificialException e){
                ae = e;
            }
        }

        finallyCommand.run(functionObject);
        if(ae != null) throw ae;
    }

    private MgCatchCommand find(ArtificialException ae, MgFunctionObject functionObject){
        if(ae instanceof RollbackException){
            RollbackException re = (RollbackException) ae;
            for(MgCatchCommand catchCommand : catchCommands){
                if(re.getObject().getType().is(catchCommand.getVariable().getDatatype().getType())){
                    functionObject.getObjects().set(re.getObject(), output);
                    return catchCommand;
                }
            }
        }

        return null;
    }
}
