package cz.mg.compiler.tasks.writers.c.command;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.commands.CExpressionCommand;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;
import cz.mg.compiler.tasks.writers.c.part.expression.CExpressionWriterTask;


public class CExpressionCommandWriterTask extends CCommandWriterTask {
    @Input
    private final CExpressionCommand command;

    @Output
    private final List<Line> lines = new List<>();

    @Subtask
    private CExpressionWriterTask expressionWriterTask = null;

    public CExpressionCommandWriterTask(CExpressionCommand command) {
        this.command = command;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        expressionWriterTask = CExpressionWriterTask.create(command.getExpression());
        expressionWriterTask.run();
        lines.addLast(new Line(expressionWriterTask.getTokens()));
        lines.getLast().getTokens().addLast(CSeparator.SEMICOLON);
    }
}
