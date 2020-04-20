package cz.mg.compiler.entities.logic.c.commands;

import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.entities.logic.c.parts.expressions.CExpression;


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
