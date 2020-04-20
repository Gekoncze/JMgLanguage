package cz.mg.compiler.tasks.writers.c.part;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.parts.CVariable;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.common.Identifier;
import cz.mg.compiler.entities.text.tokens.common.Space;


public class CVariableWriterTask extends CPartWriterTask {
    @Input
    private final CVariable variable;

    @Output
    private final List<Token> tokens = new List<>();

    @Subtask
    private CTypeWriterTask typeWriterTask = null;

    public CVariableWriterTask(CVariable variable) {
        this.variable = variable;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        writeType();
        tokens.addLast(Space.SPACE);
        writeName();
    }

    private void writeType(){
        typeWriterTask = new CTypeWriterTask(variable.getType());
        typeWriterTask.run();
        tokens.addCollectionLast(typeWriterTask.getTokens());
    }

    private void writeName(){
        tokens.addLast(new Identifier(variable.getName()));
    }
}
