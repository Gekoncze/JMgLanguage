package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalFinallyCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgFinallyCommand;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveFinallyCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalFinallyCommand logicalCommand;

    @Output
    private MgFinallyCommand command;

    public MgResolveFinallyCommandTask(CommandContext context, MgLogicalFinallyCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgFinallyCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        //todo;
    }
}
