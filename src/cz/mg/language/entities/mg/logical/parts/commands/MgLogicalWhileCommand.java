package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class MgLogicalWhileCommand extends MgLogicalBlockCommand {
    @Part
    private MgLogicalExpression expression;

    public MgLogicalWhileCommand() {
    }

    public MgLogicalWhileCommand(MgLogicalExpression expression) {
        this.expression = expression;
    }

    public MgLogicalExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalExpression expression) {
        this.expression = expression;
    }
}
