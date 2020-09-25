package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCaseCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCaseCommand;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveCaseCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalCaseCommand logicalCommand;

    @Output
    private MgCaseCommand command;

    public MgResolveCaseCommandTask(CommandContext context, MgLogicalCaseCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgCaseCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        //todo;
    }
}
