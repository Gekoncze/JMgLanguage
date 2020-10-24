package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalClumpExpression;


public class MgLogicalReturnCommand extends MgLogicalCommand {
    @Part
    private MgLogicalClumpExpression expression;

    public MgLogicalReturnCommand() {
    }

    public MgLogicalReturnCommand(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }

    public MgLogicalClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }
}
