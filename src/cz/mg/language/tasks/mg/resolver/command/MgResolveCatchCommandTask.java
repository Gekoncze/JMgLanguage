package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCatchCommand;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveCatchCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalCatchCommand logicalCommand;

    @Output
    private Command command;

    public MgResolveCatchCommandTask(CommandContext context, MgLogicalCatchCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        //todo;
    }
}
