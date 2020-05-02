package cz.mg.language.tasks.writers.c.part.expression.value;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.logic.c.parts.expressions.values.CLiteral;
import cz.mg.language.entities.text.common.tokens.c.CLiteralToken;
import cz.mg.language.entities.text.common.Token;


public class CLiteralWriterTask extends CValueWriterTask {
    @Input
    private final CLiteral literal;

    @Output
    private final List<Token> tokens = new List<>();

    public CLiteralWriterTask(CLiteral literal) {
        this.literal = literal;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        tokens.addLast(new CLiteralToken(literal.getValue()));
    }
}
