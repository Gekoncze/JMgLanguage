package cz.mg.language.entities.c.logical.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.c.logical.parts.expressions.CExpression;


public class CIfCommand extends CBlockCommand {
    @Part
    private CExpression expression;

    public CIfCommand(CExpression expression, CCommand... commands) {
        super(commands);
        this.expression = expression;
    }

    public CExpression getExpression() {
        return expression;
    }
}
