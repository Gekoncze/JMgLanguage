package cz.mg.compiler.tasks.writers.c.command;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.commands.CForCommand;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.tokens.c.CBracket;
import cz.mg.compiler.entities.text.tokens.c.CKeyword;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;
import cz.mg.compiler.tasks.writers.c.CCommandBlockWriterTask;
import cz.mg.compiler.tasks.writers.c.part.expression.CExpressionWriterTask;

import static cz.mg.compiler.tasks.writers.c.Utilities.indent;


public class CForCommandWriterTask extends CCommandWriterTask {
    @Input
    private final CForCommand command;

    @Output
    private final List<Line> lines = new List<>();

    @Subtask
    private CExpressionWriterTask initializerWriterTask = null;

    @Subtask
    private CExpressionWriterTask conditionWriterTask = null;

    @Subtask
    private CExpressionWriterTask modifierWriterTask = null;

    @Subtask
    private CCommandBlockWriterTask commandBlockWriterTask = null;

    public CForCommandWriterTask(CForCommand command) {
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

        line.getTokens().addLast(CKeyword.FOR);
        line.getTokens().addLast(CBracket.ROUND_LEFT);

        initializerWriterTask = CExpressionWriterTask.create(command.getInitializer());
        initializerWriterTask.run();
        line.getTokens().addCollectionLast(initializerWriterTask.getTokens());

        line.getTokens().addLast(CSeparator.SEMICOLON);

        conditionWriterTask = CExpressionWriterTask.create(command.getCondition());
        conditionWriterTask.run();
        line.getTokens().addCollectionLast(conditionWriterTask.getTokens());

        line.getTokens().addLast(CSeparator.SEMICOLON);

        modifierWriterTask = CExpressionWriterTask.create(command.getModifier());
        modifierWriterTask.run();
        line.getTokens().addCollectionLast(modifierWriterTask.getTokens());

        line.getTokens().addLast(CBracket.ROUND_RIGHT);
        line.getTokens().addLast(CBracket.CURLY_LEFT);

        lines.addLast(line);
    }

    private void writeCommands(){
        commandBlockWriterTask = new CCommandBlockWriterTask(command.getCommands());
        commandBlockWriterTask.run();
        lines.addCollectionLast(indent(commandBlockWriterTask.getLines()));
    }

    private void writeFooter() {
        Line line = new Line();
        line.getTokens().addLast(CBracket.CURLY_RIGHT);
        lines.addLast(line);
    }
}
