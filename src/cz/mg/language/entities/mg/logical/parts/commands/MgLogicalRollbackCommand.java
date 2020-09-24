package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalClumpExpression;


public class MgLogicalRollbackCommand extends MgLogicalCommand {
    @Part
    private MgLogicalClumpExpression expression;

    public MgLogicalRollbackCommand() {
    }

    public MgLogicalRollbackCommand(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }

    public MgLogicalClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }
}
