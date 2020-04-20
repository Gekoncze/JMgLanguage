package cz.mg.compiler.tasks.writers.c;

import cz.mg.collections.Collection;
import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.commands.CCommand;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.tasks.writers.LineWriterTask;
import cz.mg.compiler.tasks.writers.c.command.CCommandWriterTask;


public class CCommandBlockWriterTask extends CWriterTask implements LineWriterTask {
    @Input
    private final Collection<CCommand> commands;

    @Output
    private final List<Line> lines = new List<>();

    @Subtask
    protected List<CCommandWriterTask> commandWriterTasks = new List<>();

    public CCommandBlockWriterTask(Collection<CCommand> commands) {
        this.commands = commands;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        for(CCommand command : commands){
            commandWriterTasks.addLast(CCommandWriterTask.create(command));
            commandWriterTasks.getLast().run();
            lines.addCollectionLast(commandWriterTasks.getLast().getLines());
        }
    }
}
