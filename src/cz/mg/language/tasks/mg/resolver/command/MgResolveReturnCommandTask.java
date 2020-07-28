package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalReturnCommand;
import cz.mg.language.tasks.mg.resolver.Context;


public class MgResolveReturnCommandTask extends MgResolveCommandTask {
    @Input
    private final Context context;

    @Input
    private final MgLogicalReturnCommand logicalCommand;

    @Output
    private Command command;

    public MgResolveReturnCommandTask(Context context, MgLogicalReturnCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        todo;
    }
}
