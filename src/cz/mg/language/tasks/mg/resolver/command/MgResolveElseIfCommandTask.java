package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.tasks.mg.resolver.MgResolveCommandsTask;
import cz.mg.language.tasks.mg.resolver.command.expression.Expression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveElseIfCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalElseIfCommand logicalCommand;

    @Output
    private Command command;

    @Subtask
    private MgResolveExpressionTreeTask resolveExpressionTreeTask;

    @Subtask
    private MgResolveExpressionTask resolveExpressionTask;

    @Subtask
    private MgResolveCommandsTask resolveCommandsTask;

    public MgResolveElseIfCommandTask(CommandContext context, MgLogicalElseIfCommand logicalCommand) {
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

        resolveExpressionTreeTask = new MgResolveExpressionTreeTask(context, logicalCommand.getExpression());
        resolveExpressionTreeTask.run();

        resolveExpressionTask = MgResolveExpressionTask.create(context, resolveExpressionTreeTask.getLogicalCallExpression(), createBooleanParentExpression());
        resolveExpressionTask.run();
        command.setExpression(resolveExpressionTask.getExpression());

        resolveCommandsTask = new MgResolveCommandsTask(context, logicalCommand.getCommands());
        resolveCommandsTask.run();
        command.getCommands().addCollectionLast(resolveCommandsTask.getCommands());

        //todo;
    }

    private Expression createBooleanParentExpression(){
        //todo
        return null;
    }
}
