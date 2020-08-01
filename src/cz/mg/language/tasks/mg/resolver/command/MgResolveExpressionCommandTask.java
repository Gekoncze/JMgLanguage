package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalExpressionCommand;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveExpressionCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalExpressionCommand logicalCommand;

    @Output
    private Command command;

    @Subtask
    private MgResolveExpressionTask resolveExpressionTask;

    public MgResolveExpressionCommandTask(CommandContext context, MgLogicalExpressionCommand logicalCommand) {
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

        resolveExpressionTask = MgResolveExpressionTask.create(context, logicalCommand.getExpression());
        resolveExpressionTask.run();
        command.setExpression(resolveExpressionTask.getExpression());

        todo;
    }
}
