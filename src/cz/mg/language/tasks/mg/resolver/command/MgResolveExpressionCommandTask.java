package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalExpressionCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgExpressionCommand;
import cz.mg.language.tasks.mg.resolver.command.expression.Expression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveExpressionCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalExpressionCommand logicalCommand;

    @Output
    private MgExpressionCommand command;

    @Subtask
    private MgResolveExpressionTreeTask resolveExpressionTreeTask;

    @Subtask
    private MgResolveExpressionTask resolveExpressionTask;

    public MgResolveExpressionCommandTask(CommandContext context, MgLogicalExpressionCommand logicalCommand) {
        this.context = new CommandContext(context);
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgExpressionCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
//        resolveExpressionTreeTask = new MgResolveExpressionTreeTask(context, logicalCommand.getExpression());
//        resolveExpressionTreeTask.run();
//
//        resolveExpressionTask = MgResolveExpressionTask.create(context, resolveExpressionTreeTask.getLogicalCallExpression(), createVoidParentExpression());
//        resolveExpressionTask.run();
//
//        command = new MgExpressionCommand(resolveExpressionTask.getExpression());
//        context.setCommand(command); todo
    }

    private Expression createVoidParentExpression(){
        //todo
        return null;
    }
}
