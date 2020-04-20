package cz.mg.compiler.tasks.writers.c.element.statement.declaration;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CFunctionForwardDeclaration;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;
import cz.mg.compiler.tasks.writers.c.part.CFunctionSignatureWriterTask;


public class CFunctionForwardDeclarationWriterTask extends CForwardDeclarationWriterTask {
    @Input
    private final CFunctionForwardDeclaration functionDeclaration;

    @Output
    private final List<Line> lines = new List<>();

    @Subtask
    private CFunctionSignatureWriterTask functionSignatureWriterTask = null;

    public CFunctionForwardDeclarationWriterTask(CFunctionForwardDeclaration functionDeclaration) {
        this.functionDeclaration = functionDeclaration;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    protected void onRun() {
        Line line = new Line();
        functionSignatureWriterTask = new CFunctionSignatureWriterTask(functionDeclaration);
        functionSignatureWriterTask.run();
        line.getTokens().addCollectionLast(functionSignatureWriterTask.getTokens());
        line.getTokens().addLast(CSeparator.SEMICOLON);
        lines.addLast(line);
    }
}
