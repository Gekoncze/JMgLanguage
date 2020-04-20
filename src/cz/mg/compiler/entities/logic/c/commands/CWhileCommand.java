package cz.mg.compiler.entities.logic.c.commands;

import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.entities.logic.c.parts.expressions.CExpression;


public class CWhileCommand extends CBlockCommand {
    @Part
    private CExpression expression;

    public CWhileCommand(CExpression expression, CCommand... commands) {
        super(commands);
        this.expression = expression;
    }

    public CExpression getExpression() {
        return expression;
    }
}
