package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.command.MgResolveCommandTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveCommandsTask extends MgResolverTask {
    @Input
    private final CommandContext context;

    @Input
    private final ReadableCollection<MgLogicalCommand> commands;

    @Output
    private List<MgCommand> nodes = new List<>();

    public MgResolveCommandsTask(CommandContext context, ReadableCollection<MgLogicalCommand> commands) {
        this.context = context;
        this.commands = commands;
    }

    public List<MgCommand> getCommands() {
        return nodes;
    }

    @Override
    protected void onRun() {
        nodes = new List<>();
        for(MgLogicalCommand command : commands){
            MgResolveCommandTask task = MgResolveCommandTask.create(context, command);
            task.run();
            nodes.addLast(task.getCommand());
        }
    }
}
