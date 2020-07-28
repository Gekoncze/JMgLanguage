package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;


public class MgResolveCommandsTask extends MgResolverTask {
    @Input
    private final Context context;

    @Input
    private final ReadableCollection<MgLogicalCommand> commands;

    @Output
    private List<Command> nodes = new List<>();

    @Subtask
    private final List<MgResolveCommandTask> subtasks = new List<>();

    public MgResolveCommandsTask(Context context, ReadableCollection<MgLogicalCommand> commands) {
        this.context = context;
        this.commands = commands;
    }

    public List<Command> getNodes() {
        return nodes;
    }

    @Override
    protected void onRun() {
        nodes = new List<>();
        for(MgLogicalCommand command : commands){
            subtasks.addLast(MgResolveCommandTask.create(context, command));
            subtasks.getLast().run();
            nodes.addLast(subtasks.getLast().getCommand());
        }
    }
}
