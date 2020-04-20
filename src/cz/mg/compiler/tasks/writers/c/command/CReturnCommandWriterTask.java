package cz.mg.compiler.tasks.writers.c.command;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.commands.CReturnCommand;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.tokens.c.CKeyword;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;
import cz.mg.compiler.tasks.writers.c.part.expression.CExpressionWriterTask;


public class CReturnCommandWriterTask extends CCommandWriterTask {
    @Input
    private final CReturnCommand command;

    @Output
    private final List<Line> lines = new List<>();

    @Subtask
    private CExpressionWriterTask expressionWriterTask = null;

    public CReturnCommandWriterTask(CReturnCommand command) {
        this.command = command;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        Line line = new Line();
        line.getTokens().addLast(CKeyword.RETURN);
        if(command.getExpression() != null){
            expressionWriterTask = CExpressionWriterTask.create(command.getExpression());
            expressionWriterTask.run();
            line.getTokens().addCollectionLast(expressionWriterTask.getTokens());
        }
        line.getTokens().addLast(CSeparator.SEMICOLON);
        lines.addLast(line);
    }
}
