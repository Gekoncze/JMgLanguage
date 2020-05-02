package cz.mg.language.entities.logic.c.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.c.parts.expressions.CExpression;


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
