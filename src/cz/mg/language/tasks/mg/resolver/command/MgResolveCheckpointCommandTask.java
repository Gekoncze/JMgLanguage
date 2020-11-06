package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCatchCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCheckpointCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCatchCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCheckpointCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgFinallyCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgTryCommand;
import cz.mg.language.tasks.mg.resolver.context.executable.CommandContext;


public class MgResolveCheckpointCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalCheckpointCommand logicalCommand;

    @Output
    private MgCheckpointCommand command;

    public MgResolveCheckpointCommandTask(CommandContext context, MgLogicalCheckpointCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgCheckpointCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        MgTryCommand tryCommand = null;
        if(logicalCommand.getTryCommand() != null){
            MgResolveTryCommandTask resolveTryCommandTask = new MgResolveTryCommandTask(context, logicalCommand.getTryCommand());
            resolveTryCommandTask.run();
            tryCommand = resolveTryCommandTask.getCommand();
        }

        List<MgCatchCommand> catchCommands = null;
        if(logicalCommand.getCatchCommands() != null){
            catchCommands = new List<>();
            for(MgLogicalCatchCommand logicalCatchCommand : logicalCommand.getCatchCommands()){
                MgResolveCatchCommandTask resolveCatchCommandTask = new MgResolveCatchCommandTask(context, logicalCatchCommand);
                resolveCatchCommandTask.run();
                catchCommands.addLast(resolveCatchCommandTask.getCommand());
            }
        }

        MgFinallyCommand finallyCommand = null;
        if(logicalCommand.getFinallyCommand() != null){
            MgResolveFinallyCommandTask resolveFinallyCommandTask = new MgResolveFinallyCommandTask(context, logicalCommand.getFinallyCommand());
            resolveFinallyCommandTask.run();
            finallyCommand = resolveFinallyCommandTask.getCommand();
        }

        command = new MgCheckpointCommand(tryCommand, catchCommands, finallyCommand);
        context.setCommand(command);
    }
}
