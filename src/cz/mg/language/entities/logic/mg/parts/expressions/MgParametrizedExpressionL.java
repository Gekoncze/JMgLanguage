package cz.mg.language.entities.logic.mg.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.parts.expressions.chains.MgListExpressionL;


public class MgParametrizedExpressionL extends MgExpressionL {
    @Value
    private final MgNameExpressionL target;

    @Part
    private final MgListExpressionL arguments;

    public MgParametrizedExpressionL(MgNameExpressionL target, MgListExpressionL arguments) {
        this.target = target;
        this.arguments = arguments;
    }

    public MgNameExpressionL getTarget() {
        return target;
    }

    public MgListExpressionL getArguments() {
        return arguments;
    }
}
