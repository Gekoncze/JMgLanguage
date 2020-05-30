package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.MgUsageL;


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
