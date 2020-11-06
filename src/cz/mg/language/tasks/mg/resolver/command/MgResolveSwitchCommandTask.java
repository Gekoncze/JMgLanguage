package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCaseCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalElseCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalSwitchCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgSwitchCommand;
import cz.mg.language.tasks.mg.resolver.context.executable.CommandContext;


public class MgResolveSwitchCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalSwitchCommand logicalCommand;

    @Output
    private MgSwitchCommand command;

    public MgResolveSwitchCommandTask(CommandContext context, MgLogicalSwitchCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgSwitchCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        command = new MgSwitchCommand();
        context.setCommand(command);

        for(MgLogicalCaseCommand logicalCaseCommand : logicalCommand.getCommands()){
            if(logicalCaseCommand instanceof MgLogicalElseCommand){
                MgResolveElseCommandTask task = new MgResolveElseCommandTask(context, (MgLogicalElseCommand) logicalCaseCommand);
                task.run();
                command.getCaseCommands().addLast(task.getCommand());
            } else {
                MgResolveCaseCommandTask task = new MgResolveCaseCommandTask(context, logicalCaseCommand);
                task.run();
                command.getCaseCommands().addLast(task.getCommand());
            }
        }
    }
}
