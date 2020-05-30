package cz.mg.language.entities.c.logical.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.c.logical.parts.expressions.CExpression;


public class CExpressionCommand extends CBlockCommand {
    @Part
    private CExpression expression;

    public CExpressionCommand() {
        this.expression = null;
    }

    public CExpressionCommand(CExpression expression) {
        this.expression = expression;
    }

    public CExpression getExpression() {
        return expression;
    }
}
