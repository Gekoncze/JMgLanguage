package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalClumpExpression;


public class MgLogicalIfCommand extends MgLogicalBlockCommand {
    @Optional @Part
    private MgLogicalClumpExpression expression;

    public MgLogicalIfCommand() {
    }

    public MgLogicalIfCommand(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }

    public MgLogicalClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }
}
