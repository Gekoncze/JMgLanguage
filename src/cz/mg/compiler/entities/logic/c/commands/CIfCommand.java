package cz.mg.compiler.entities.logic.c.commands;

import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.entities.logic.c.parts.expressions.CExpression;


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
