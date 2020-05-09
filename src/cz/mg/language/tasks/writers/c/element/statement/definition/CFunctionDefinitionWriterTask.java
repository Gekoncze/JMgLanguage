package cz.mg.language.tasks.writers.c.element.statement.definition;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.logic.c.elements.statements.definitions.CFunctionDefinition;
import cz.mg.language.entities.text.linear.Line;
import cz.mg.language.entities.text.linear.tokens.c.CBracketToken;
import cz.mg.language.tasks.writers.c.CCommandBlockWriterTask;
import cz.mg.language.tasks.writers.c.part.CFunctionSignatureWriterTask;

import static cz.mg.language.tasks.writers.c.Utilities.indent;


public class CFunctionDefinitionWriterTask extends CDefinitionWriterTask {
    @Input
    private final CFunctionDefinition functionDefinition;

    @Output
    private final List<Line> lines = new List<>();

    @Subtask
    private CFunctionSignatureWriterTask functionSignatureWriterTask = null;

    @Subtask
    private CCommandBlockWriterTask commandBlockWriterTask = null;

    public CFunctionDefinitionWriterTask(CFunctionDefinition functionDefinition) {
        this.functionDefinition = functionDefinition;
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
        functionSignatureWriterTask = new CFunctionSignatureWriterTask(functionDefinition.getDeclaration());
        functionSignatureWriterTask.run();
        line.getTokens().addCollectionLast(functionSignatureWriterTask.getTokens());
        line.getTokens().addLast(CBracketToken.CURLY_LEFT);
        lines.addLast(line);
    }

    private void writeCommands(){
        commandBlockWriterTask = new CCommandBlockWriterTask(functionDefinition.getCommands());
        commandBlockWriterTask.run();
        lines.addCollectionLast(indent(commandBlockWriterTask.getLines()));
    }

    private void writeFooter() {
        Line line = new Line();
        line.getTokens().addLast(CBracketToken.CURLY_RIGHT);
        lines.addLast(line);
    }
}
