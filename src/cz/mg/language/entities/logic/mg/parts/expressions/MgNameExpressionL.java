package cz.mg.language.entities.logic.mg.parts.expressions;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.parts.MgUsageL;


public class MgNameExpressionL extends MgExpressionL {
    @Value
    private final MgUsageL target;

    public MgNameExpressionL(MgUsageL target) {
        this.target = target;
    }

    public MgUsageL getTarget() {
        return target;
    }
}
