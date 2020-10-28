package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCatchCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCatchCommand;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.component.MgResolveFunctionVariableDefinitionTask;


public class MgResolveCatchCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalCatchCommand logicalCommand;

    @Output
    private MgCatchCommand command;

    public MgResolveCatchCommandTask(CommandContext context, MgLogicalCatchCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgCatchCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        MgResolveFunctionVariableDefinitionTask task = new MgResolveFunctionVariableDefinitionTask(
            context, context.getFunctionContext().getFunction(), logicalCommand.getVariable()
        );
        task.run();

        command = new MgCatchCommand(task.getVariable(), new List<>());
        context.setCommand(command);

        for(MgLogicalCommand logicalCommand : logicalCommand.getCommands()){
            MgResolveCommandTask resolveCommandTask = MgResolveCommandTask.create(context, logicalCommand);
            resolveCommandTask.run();
            command.getCommands().addLast(resolveCommandTask.getCommand());
        }
    }
}
