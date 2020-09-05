package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalIfCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgIfCommand;
import cz.mg.language.tasks.mg.resolver.MgResolveCommandsTask;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveIfCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalIfCommand logicalCommand;

    @Output
    private MgIfCommand command;

    @Subtask
    private MgResolveExpressionTreeTask resolveExpressionTreeTask;

    @Subtask
    private MgResolveExpressionTask resolveExpressionTask;

    @Subtask
    private MgResolveCommandsTask resolveCommandsTask;

    public MgResolveIfCommandTask(CommandContext context, MgLogicalIfCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgIfCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
//        command = new Command(context, logicalCommand);
//
//        resolveExpressionTreeTask = new MgResolveExpressionTreeTask(context, logicalCommand.getExpression());
//        resolveExpressionTreeTask.run();
//
//        resolveExpressionTask = MgResolveExpressionTask.create(context, resolveExpressionTreeTask.getLogicalCallExpression(), createBooleanParentExpression());
//        resolveExpressionTask.run();
//        command.setExpression(resolveExpressionTask.getExpression());
//
//        resolveCommandsTask = new MgResolveCommandsTask(context, logicalCommand.getCommands());
//        resolveCommandsTask.run();
//        command.getCommands().addCollectionLast(resolveCommandsTask.getCommands());

        //todo;
    }

//    private Expression createBooleanParentExpression(){
//        //todo - //todo - add command-level expression resolver, so we can reuse its connect logic
//        return null;
//    }
}
