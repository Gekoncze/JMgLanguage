package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalElseCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgElseCommand;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveElseCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalElseCommand logicalCommand;

    @Output
    private MgElseCommand command;

    public MgResolveElseCommandTask(CommandContext context, MgLogicalElseCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgElseCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        //todo;
    }
}
