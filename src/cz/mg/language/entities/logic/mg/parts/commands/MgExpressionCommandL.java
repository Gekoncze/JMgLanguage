package cz.mg.language.entities.logic.mg.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.mg.parts.expressions.MgExpressionL;


public class MgExpressionCommandL extends MgCommandL {
    @Part
    private final MgExpressionL expression;

    public MgExpressionCommandL(MgExpressionL expression) {
        this.expression = expression;
    }

    public MgExpressionL getExpression() {
        return expression;
    }
}
