package cz.mg.language.tasks.writers.c.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.logic.c.commands.CElseIfCommand;
import cz.mg.language.entities.text.common.Line;
import cz.mg.language.entities.text.c.tokens.CBracketToken;
import cz.mg.language.entities.text.c.tokens.CKeywordToken;
import cz.mg.language.tasks.writers.c.CCommandBlockWriterTask;
import cz.mg.language.tasks.writers.c.part.expression.CExpressionWriterTask;

import static cz.mg.language.tasks.writers.c.Utilities.indent;


public class CElseIfCommandWriterTask extends CCommandWriterTask {
    @Input
    private final CElseIfCommand command;

    @Output
    private final List<Line> lines = new List<>();

    @Subtask
    private CExpressionWriterTask expressionWriterTask = null;

    @Subtask
    private CCommandBlockWriterTask commandBlockWriterTask = null;

    public CElseIfCommandWriterTask(CElseIfCommand command) {
        this.command = command;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        writeHeader();
        writeCommands();
        writeFooter();
    }

    private void writeHeader(){
        Line line = new Line();

        line.getTokens().addLast(CKeywordToken.ELSE);
        line.getTokens().addLast(CKeywordToken.IF);

        line.getTokens().addLast(CBracketToken.ROUND_LEFT);
        expressionWriterTask = CExpressionWriterTask.create(command.getExpression());
        expressionWriterTask.run();
        line.getTokens().addCollectionLast(expressionWriterTask.getTokens());
        line.getTokens().addLast(CBracketToken.ROUND_RIGHT);

        line.getTokens().addLast(CBracketToken.CURLY_LEFT);

        lines.addLast(line);
    }

    private void writeCommands(){
        commandBlockWriterTask = new CCommandBlockWriterTask(command.getCommands());
        commandBlockWriterTask.run();
        lines.addCollectionLast(indent(commandBlockWriterTask.getLines()));
    }

    private void writeFooter() {
        Line line = new Line();
        line.getTokens().addLast(CBracketToken.CURLY_RIGHT);
        lines.addLast(line);
    }
}
