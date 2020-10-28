package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalIfCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgIfCommand;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTreeTask;
import cz.mg.language.tasks.mg.resolver.command.expression.root.MgResolveNonVoidExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveIfCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalIfCommand logicalCommand;

    @Output
    private MgIfCommand command;

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
        MgResolveExpressionTreeTask resolveExpressionTreeTask = new MgResolveExpressionTreeTask(context, logicalCommand.getExpression());
        resolveExpressionTreeTask.run();

        MgResolveNonVoidExpressionTask resolveExpressionTask = new MgResolveNonVoidExpressionTask(context, resolveExpressionTreeTask.getLogicalCallExpression());
        resolveExpressionTask.run();
        command = new MgIfCommand(resolveExpressionTask.getExpression());
        context.setCommand(command);
        resolveExpressionTask.connect(command.getInputConnector());

        for(MgLogicalCommand logicalCommand : logicalCommand.getCommands()){
            MgResolveCommandTask resolveCommandTask = MgResolveCommandTask.create(context, logicalCommand);
            resolveCommandTask.run();
            command.getCommands().addLast(resolveCommandTask.getCommand());
        }
    }
}
