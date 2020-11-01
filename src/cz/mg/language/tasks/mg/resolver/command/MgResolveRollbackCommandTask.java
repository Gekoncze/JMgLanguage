package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalRollbackCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgRollbackCommand;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
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

        MgResolveExpressionTask resolveExpressionTask = MgResolveExpressionTask.create(
            context,
            resolveExpressionTreeTask.getLogicalCallExpression(),
            new ExpectedParentInput(MgRollbackCommand.DATATYPE)
        );
        resolveExpressionTask.run();
        command = new MgRollbackCommand(resolveExpressionTask.getExpression());
        context.setCommand(command);
    }
}
