package cz.mg.compiler.tasks.writers.c.command;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.entities.logic.c.commands.CContinueCommand;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.tokens.c.CKeyword;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;


public class CContinueCommandWriterTask extends CCommandWriterTask {
    @Input
    private final CContinueCommand command;

    @Output
    private final List<Line> lines = new List<>();

    public CContinueCommandWriterTask(CContinueCommand command) {
        this.command = command;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        lines.addLast(new Line(CKeyword.CONTINUE, CSeparator.SEMICOLON));
    }
}
