package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalClumpExpression;


public class MgLogicalExpressionCommand extends MgLogicalCommand {
    @Part
    private MgLogicalClumpExpression expression;

    public MgLogicalExpressionCommand() {
    }

    public MgLogicalExpressionCommand(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }

    public MgLogicalClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }
}
