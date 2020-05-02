package cz.mg.language.entities.logic.mg.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.mg.expressions.MgExpression;


public class MgReturnCommand extends MgCommand {
    @Part
    private final MgExpression expression;

    public MgReturnCommand(MgExpression expression) {
        this.expression = expression;
    }

    public MgExpression getExpression() {
        return expression;
    }
}
