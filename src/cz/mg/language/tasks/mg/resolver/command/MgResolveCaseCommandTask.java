package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCaseCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCaseCommand;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveCaseCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalCaseCommand logicalCommand;

    @Output
    private MgCaseCommand command;

    public MgResolveCaseCommandTask(CommandContext context, MgLogicalCaseCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgCaseCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        MgResolveExpressionTreeTask resolveExpressionTreeTask = new MgResolveExpressionTreeTask(context, logicalCommand.getExpression());
        resolveExpressionTreeTask.run();

        MgResolveExpressionTask resolveExpressionTask = MgResolveExpressionTask.create(context, resolveExpressionTreeTask.getLogicalCallExpression(), null /*todo*/); new Todo();
        resolveExpressionTask.run();
        command = new MgCaseCommand(resolveExpressionTask.getExpression());
        context.setCommand(command);

        for(MgLogicalCommand logicalCommand : logicalCommand.getCommands()){
            MgResolveCommandTask resolveCommandTask = MgResolveCommandTask.create(context, logicalCommand);
            resolveCommandTask.run();
            command.getCommands().addLast(resolveCommandTask.getCommand());
        }
    }
}
