package cz.mg.language.entities.logic.c.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.c.parts.expressions.CExpression;


public class CReturnCommand extends CCommand {
    @Part
    private CExpression expression;

    public CReturnCommand() {
        this.expression = null;
    }

    public CReturnCommand(CExpression expression) {
        this.expression = expression;
    }

    public CExpression getExpression() {
        return expression;
    }
}
