package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalClumpExpression;


public class MgLogicalCaseCommand extends MgLogicalBlockCommand {
    @Optional @Part
    private MgLogicalClumpExpression expression;

    public MgLogicalCaseCommand() {
    }

    public MgLogicalCaseCommand(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }

    public MgLogicalClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }
}
