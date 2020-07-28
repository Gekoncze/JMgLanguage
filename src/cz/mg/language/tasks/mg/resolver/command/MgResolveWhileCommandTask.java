package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalWhileCommand;
import cz.mg.language.tasks.mg.resolver.Context;


public class MgResolveWhileCommandTask extends MgResolveCommandTask {
    @Input
    private final Context context;

    @Input
    private final MgLogicalWhileCommand logicalCommand;

    @Output
    private Command command;

    public MgResolveWhileCommandTask(Context context, MgLogicalWhileCommand logicalCommand) {
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
