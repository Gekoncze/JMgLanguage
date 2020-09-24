package cz.mg.language.entities.c.logical.commands;

import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.c.logical.parts.expressions.CExpression;


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
