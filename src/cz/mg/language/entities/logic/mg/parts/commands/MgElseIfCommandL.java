package cz.mg.language.entities.logic.mg.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.mg.parts.expressions.MgExpressionL;


public class MgElseIfCommandL extends MgBlockCommandL {
    @Part
    private final MgExpressionL expression;

    public MgElseIfCommandL(MgExpressionL expression) {
        this.expression = expression;
    }

    public MgExpressionL getExpression() {
        return expression;
    }
}
