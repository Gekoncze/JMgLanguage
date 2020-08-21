package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalGroupExpression;


public class MgLogicalWhileCommand extends MgLogicalBlockCommand {
    @Part
    private MgLogicalGroupExpression expression;

    public MgLogicalWhileCommand() {
    }

    public MgLogicalWhileCommand(MgLogicalGroupExpression expression) {
        this.expression = expression;
    }

    public MgLogicalGroupExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalGroupExpression expression) {
        this.expression = expression;
    }
}
