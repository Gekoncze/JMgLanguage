package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ArtificialException;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.RollbackException;


public class MgCheckpointCommand extends MgCommand {
    @Optional @Part
    private final MgTryCommand tryCommand;

    @Mandatory @Part
    private final List<MgCatchCommand> catchCommands;

    @Optional @Part
    private final MgFinallyCommand finallyCommand;

    public MgCheckpointCommand(MgTryCommand tryCommand, List<MgCatchCommand> catchCommands, MgFinallyCommand finallyCommand) {
        this.tryCommand = tryCommand;
        this.catchCommands = catchCommands;
        this.finallyCommand = finallyCommand;
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
    public void run(MgFunctionInstance functionInstance) {
        ArtificialException ae = null;
        try {
            if(tryCommand != null) tryCommand.run(functionInstance);
        } catch (ArtificialException e){
            ae = e;
        }

        MgCatchCommand handler = find(ae, functionInstance);
        if(handler != null){
            ae = null;
            try {
                handler.run(functionInstance);
            } catch (ArtificialException e){
                ae = e;
            }
        }

        if(finallyCommand != null) finallyCommand.run(functionInstance);
        if(ae != null) throw ae;
    }

    private MgCatchCommand find(ArtificialException ae, MgFunctionInstance functionObject){
        if(ae instanceof RollbackException){
            RollbackException re = (RollbackException) ae;
            for(MgCatchCommand catchCommand : catchCommands){
                if(re.getObject().getType().is(catchCommand.getInput().getDatatype().getType())){
                    functionObject.getObjects().set(re.getObject(), catchCommand.getInput().getOffset());
                    return catchCommand;
                }
            }
        }

        return null;
    }
}
