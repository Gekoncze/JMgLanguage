package cz.mg.language.entities.logic.mg.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.mg.parts.expressions.MgExpressionL;


public class MgIfCommandL extends MgBlockCommandL {
    @Part
    private final MgExpressionL expression;

    public MgIfCommandL(MgExpressionL expression) {
        this.expression = expression;
    }

    public MgExpressionL getExpression() {
        return expression;
    }
}
