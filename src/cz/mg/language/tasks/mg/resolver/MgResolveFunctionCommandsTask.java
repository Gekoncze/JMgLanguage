package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.contexts.FunctionContext;


public class MgResolveFunctionCommandsTask extends MgResolverTask {
    @Input
    private final FunctionContext context;

    @Input
    private final MgLogicalFunction logicalFunction;

    @Input
    private final MgFunction function;

    @Subtask
    private MgResolveCommandsTask subtask;

    public MgResolveFunctionCommandsTask(FunctionContext context, MgLogicalFunction logicalFunction, MgFunction function) {
        this.context = context;
        this.logicalFunction = logicalFunction;
        this.function = function;
    }

    @Override
    protected void onRun() {
        subtask = new MgResolveCommandsTask(new CommandContext(context), logicalFunction.getCommands());
        subtask.run();
        List<MgCommand> commands = subtask.getCommands();

        //function.setLocal(new Array<>(variables));

        //todo;
    }
}
