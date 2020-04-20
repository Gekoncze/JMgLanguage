package cz.mg.compiler.tasks.writers.c.part.expression;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.parts.expressions.CDeclaration;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.tasks.writers.c.part.CVariableWriterTask;
import cz.mg.compiler.tasks.writers.c.part.expression.value.CValueWriterTask;


public class CDeclarationWriterTask extends CValueWriterTask {
    @Input
    private final CDeclaration declaration;

    @Output
    private final List<Token> tokens = new List<>();

    @Subtask
    private CVariableWriterTask variableWriterTask = null;

    public CDeclarationWriterTask(CDeclaration declaration) {
        this.declaration = declaration;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        variableWriterTask = new CVariableWriterTask(declaration.getVariable());
        variableWriterTask.run();
        tokens.addCollectionLast(variableWriterTask.getTokens());
    }
}
