package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalGroupExpression;


public class MgLogicalExpressionCommand extends MgLogicalCommand {
    @Part
    private MgLogicalGroupExpression expression;

    public MgLogicalExpressionCommand() {
    }

    public MgLogicalExpressionCommand(MgLogicalGroupExpression expression) {
        this.expression = expression;
    }

    public MgLogicalGroupExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalGroupExpression expression) {
        this.expression = expression;
    }
}
