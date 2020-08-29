package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalContinueCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgContinueCommand;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveContinueCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalContinueCommand logicalCommand;

    @Output
    private MgContinueCommand command;

    public MgResolveContinueCommandTask(CommandContext context, MgLogicalContinueCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgContinueCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
//        command = new Command(context, logicalCommand);

        //todo;
    }
}
