package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class MgLogicalExpressionCommand extends MgLogicalCommand {
    @Part
    private MgLogicalExpression expression;

    public MgLogicalExpressionCommand() {
    }

    public MgLogicalExpressionCommand(MgLogicalExpression expression) {
        this.expression = expression;
    }

    public MgLogicalExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalExpression expression) {
        this.expression = expression;
    }
}
