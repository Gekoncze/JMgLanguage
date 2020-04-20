package cz.mg.compiler.entities.logic.mg.commands;

import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.entities.logic.mg.expressions.MgExpression;


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
