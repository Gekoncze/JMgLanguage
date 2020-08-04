package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBreakCommand;
import cz.mg.language.entities.mg.runtime.instructions.MgJumpInstruction;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveBreakCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalBreakCommand logicalCommand;

    @Output
    private Command command;

    public MgResolveBreakCommandTask(CommandContext context, MgLogicalBreakCommand logicalCommand) {
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
        command.getInstructions().addLast(new MgJumpInstruction());

        //todo;
    }
}
