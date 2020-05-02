package cz.mg.language.entities.logic.mg.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.mg.expressions.MgExpression;


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
