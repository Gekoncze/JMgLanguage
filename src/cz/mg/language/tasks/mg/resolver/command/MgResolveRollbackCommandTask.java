package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalRollbackCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgRollbackCommand;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
import cz.mg.language.tasks.mg.resolver.command.expression.special.MgResolveExceptionExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveRollbackCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalRollbackCommand logicalCommand;

    @Output
    private MgRollbackCommand command;

    public MgResolveRollbackCommandTask(CommandContext context, MgLogicalRollbackCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgRollbackCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        MgResolveExpressionTreeTask resolveExpressionTreeTask = new MgResolveExpressionTreeTask(context, logicalCommand.getExpression());
        resolveExpressionTreeTask.run();

        MgResolveExceptionExpressionTask resolveExpressionTask = new MgResolveExceptionExpressionTask(context, resolveExpressionTreeTask.getLogicalCallExpression());
        resolveExpressionTask.run();

        new Todo();
//        command = new MgRollbackCommand(resolveExpressionTask.createExpression());

        new Todo(); // todo - connect expression output to command input
        resolveExpressionTask.getExpression().validate();
    }
}
