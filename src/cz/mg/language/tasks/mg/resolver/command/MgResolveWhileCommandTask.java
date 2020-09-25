package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalWhileCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgWhileCommand;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveWhileCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalWhileCommand logicalCommand;

    @Output
    private MgWhileCommand command;

    public MgResolveWhileCommandTask(CommandContext context, MgLogicalWhileCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgWhileCommand getCommand() {
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
