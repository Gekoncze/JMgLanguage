package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCaseCommand;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveCaseCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalCaseCommand logicalCommand;

    @Output
    private Command command;

    public MgResolveCaseCommandTask(CommandContext context, MgLogicalCaseCommand logicalCommand) {
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
