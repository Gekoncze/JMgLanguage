package cz.mg.language.entities.logic.mg.parts.expressions.operators;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.mg.parts.expressions.MgExpressionL;


public class MgBinaryOperatorExpressionL extends MgOperatorExpressionL {
    @Part
    private final MgExpressionL left;

    @Part
    private final MgExpressionL middle;

    @Part
    private final MgExpressionL right;

    public MgBinaryOperatorExpressionL(MgExpressionL left, MgExpressionL middle, MgExpressionL right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public MgExpressionL getLeft() {
        return left;
    }

    public MgExpressionL getMiddle() {
        return middle;
    }

    public MgExpressionL getRight() {
        return right;
    }
}
