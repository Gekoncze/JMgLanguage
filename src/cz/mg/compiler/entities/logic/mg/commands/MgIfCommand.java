package cz.mg.compiler.entities.logic.mg.commands;

import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.entities.logic.mg.expressions.MgExpression;


public class MgIfCommand extends MgBlockCommand {
    @Part
    private final MgExpression expression;

    public MgIfCommand(MgExpression expression) {
        this.expression = expression;
    }

    public MgExpression getExpression() {
        return expression;
    }
}
