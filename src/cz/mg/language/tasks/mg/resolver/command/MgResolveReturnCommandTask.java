package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalReturnCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgReturnCommand;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
import cz.mg.language.tasks.mg.resolver.command.expression.root.MgResolveVoidExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveReturnCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalReturnCommand logicalCommand;

    @Output
    private MgReturnCommand command;

    public MgResolveReturnCommandTask(CommandContext context, MgLogicalReturnCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgReturnCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        MgResolveExpressionTreeTask resolveExpressionTreeTask = new MgResolveExpressionTreeTask(context, logicalCommand.getExpression());
        resolveExpressionTreeTask.run();

        MgResolveVoidExpressionTask resolveExpressionTask = new MgResolveVoidExpressionTask(context, resolveExpressionTreeTask.getLogicalCallExpression());
        resolveExpressionTask.run();
        command = new MgReturnCommand(resolveExpressionTask.getExpression());
        context.setCommand(command);
        resolveExpressionTask.connect(null);
    }
}
