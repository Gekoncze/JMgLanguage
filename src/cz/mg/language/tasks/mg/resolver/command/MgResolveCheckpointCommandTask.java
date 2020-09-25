package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCheckpointCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCheckpointCommand;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


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
        //todo;
    }
}
