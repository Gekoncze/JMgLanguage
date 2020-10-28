package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalElseCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCaseCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgElseCommand;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveElseCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalElseCommand logicalCommand;

    @Output
    private MgElseCommand command;

    public MgResolveElseCommandTask(CommandContext context, MgLogicalElseCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgElseCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        command = new MgElseCommand();
        context.setCommand(command);

        for(MgLogicalCommand logicalCommand : logicalCommand.getCommands()){
            MgResolveCommandTask resolveCommandTask = MgResolveCommandTask.create(context, logicalCommand);
            resolveCommandTask.run();
            command.getCommands().addLast(resolveCommandTask.getCommand());
        }
    }
}
