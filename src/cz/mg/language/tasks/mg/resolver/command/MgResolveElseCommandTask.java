package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.tasks.mg.resolver.MgResolveCommandsTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveElseCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalElseCommand logicalCommand;

    @Output
    private Command command;

    @Subtask
    private MgResolveCommandsTask resolveCommandsTask;

    public MgResolveElseCommandTask(CommandContext context, MgLogicalElseCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        command = new Command(context, logicalCommand);
        resolveCommandsTask = new MgResolveCommandsTask(context, logicalCommand.getCommands());
        resolveCommandsTask.run();
        command.getCommands().addCollectionLast(resolveCommandsTask.getCommands());

        //todo;
    }
}
