package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalGroupExpression;


public class MgLogicalIfCommand extends MgLogicalBlockCommand {
    @Part
    private MgLogicalGroupExpression expression;

    public MgLogicalIfCommand() {
    }

    public MgLogicalIfCommand(MgLogicalGroupExpression expression) {
        this.expression = expression;
    }

    public MgLogicalGroupExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalGroupExpression expression) {
        this.expression = expression;
    }
}
