package cz.mg.language.entities.logic.mg.parts.expressions.operators;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.mg.parts.expressions.MgExpressionL;


public class MgUnaryOperatorExpressionL extends MgOperatorExpressionL {
    @Part
    private final MgExpressionL left;

    @Part
    private final MgExpressionL right;

    public MgUnaryOperatorExpressionL(MgExpressionL left, MgExpressionL right) {
        this.left = left;
        this.right = right;
    }

    public MgExpressionL getLeft() {
        return left;
    }

    public MgExpressionL getRight() {
        return right;
    }
}
