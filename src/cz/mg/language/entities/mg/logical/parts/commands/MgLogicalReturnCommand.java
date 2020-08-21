package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalGroupExpression;


public class MgLogicalReturnCommand extends MgLogicalCommand {
    @Part
    private MgLogicalGroupExpression expression;

    public MgLogicalReturnCommand() {
    }

    public MgLogicalReturnCommand(MgLogicalGroupExpression expression) {
        this.expression = expression;
    }

    public MgLogicalGroupExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalGroupExpression expression) {
        this.expression = expression;
    }
}
