package cz.mg.language.tasks.writers.c.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.logic.c.commands.CBreakCommand;
import cz.mg.language.entities.text.common.Line;
import cz.mg.language.entities.text.c.tokens.CKeywordToken;
import cz.mg.language.entities.text.c.tokens.CSeparatorToken;


public class CBreakCommandWriterTask extends CCommandWriterTask {
    @Input
    private final CBreakCommand command;

    @Output
    private final List<Line> lines = new List<>();

    public CBreakCommandWriterTask(CBreakCommand command) {
        this.command = command;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        lines.addLast(new Line(CKeywordToken.BREAK, CSeparatorToken.SEMICOLON));
    }
}
