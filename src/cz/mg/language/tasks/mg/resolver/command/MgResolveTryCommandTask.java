package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalTryCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgTryCommand;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


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
        //todo;
    }
}
