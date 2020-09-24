package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalClumpExpression;


public class MgLogicalWhileCommand extends MgLogicalBlockCommand {
    @Part
    private MgLogicalClumpExpression expression;

    public MgLogicalWhileCommand() {
    }

    public MgLogicalWhileCommand(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }

    public MgLogicalClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }
}
