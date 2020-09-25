package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalTryCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgTryCommand;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveTryCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalTryCommand logicalCommand;

    @Output
    private MgTryCommand command;

    public MgResolveTryCommandTask(CommandContext context, MgLogicalTryCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgTryCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        command = new MgTryCommand(new List<>());

        for(MgLogicalCommand logicalCommand : logicalCommand.getCommands()){
            MgResolveCommandTask resolveCommandTask = MgResolveCommandTask.create(context, logicalCommand);
            resolveCommandTask.run();
            command.getCommands().addLast(resolveCommandTask.getCommand());
        }
    }
}
