package cz.mg.compiler.tasks.writers.c.part.expression.value;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.entities.logic.c.parts.expressions.values.CLiteral;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.common.Literal;


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
        tokens.addLast(new Literal(literal.getValue()));
    }
}
