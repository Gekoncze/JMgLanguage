package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveFunctionCommandsTask extends MgResolverTask {
    @Input
    private final Context context;

    @Input
    private final MgLogicalFunction logicalFunction;

    @Input
    private final MgFunction function;

    @Subtask
    private MgResolveCommandsTask subtask;

    public MgResolveFunctionCommandsTask(Context context, MgLogicalFunction logicalFunction, MgFunction function) {
        this.context = context;
        this.logicalFunction = logicalFunction;
        this.function = function;
    }

    @Override
    protected void onRun() {
        subtask = new MgResolveCommandsTask(new CommandContext(context), logicalFunction.getCommands());
        subtask.run();
        List<Command> nodes = subtask.getNodes();

        //todo; // todo - at this point we have all nodes connected and resolved
        // todo - so we can create instructions from them
        // todo - collect all those instructions in a list
        // todo - also collect all local variables in a list

        //function.setInstructions(new Array<>(instructions));
        //function.setLocal(new Array<>(variables));
    }
}
