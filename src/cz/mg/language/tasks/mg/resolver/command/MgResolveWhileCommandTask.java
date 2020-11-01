package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalWhileCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgWhileCommand;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
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
        MgResolveExpressionTreeTask resolveExpressionTreeTask = new MgResolveExpressionTreeTask(context, logicalCommand.getExpression());
        resolveExpressionTreeTask.run();

        MgResolveExpressionTask resolveExpressionTask = MgResolveExpressionTask.create(context, resolveExpressionTreeTask.getLogicalCallExpression(), null /*todo*/); new Todo();
        resolveExpressionTask.run();
        command = new MgWhileCommand(logicalCommand.getName(), resolveExpressionTask.getExpression());
        context.setCommand(command);

        for(MgLogicalCommand logicalCommand : logicalCommand.getCommands()){
            MgResolveCommandTask resolveCommandTask = MgResolveCommandTask.create(context, logicalCommand);
            resolveCommandTask.run();
            command.getCommands().addLast(resolveCommandTask.getCommand());
        }
    }
}
