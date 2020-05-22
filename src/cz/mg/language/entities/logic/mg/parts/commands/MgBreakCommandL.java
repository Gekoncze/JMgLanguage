package cz.mg.language.entities.logic.mg.parts.commands;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.parts.MgUsageL;


public class MgBreakCommandL extends MgCommandL {
    @Value
    private final MgUsageL target;

    public MgBreakCommandL() {
        this(null);
    }

    public MgBreakCommandL(MgUsageL target) {
        this.target = target;
    }

    public MgUsageL getTarget() {
        return target;
    }
}
