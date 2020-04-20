package cz.mg.compiler.tasks.writers.c.part.expression.value;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.entities.logic.c.parts.expressions.values.CName;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.common.Identifier;


public class CNameWriterTask extends CValueWriterTask {
    @Input
    private final CName name;

    @Output
    private final List<Token> tokens = new List<>();

    public CNameWriterTask(CName name) {
        this.name = name;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        tokens.addLast(new Identifier(name.getName()));
    }
}
