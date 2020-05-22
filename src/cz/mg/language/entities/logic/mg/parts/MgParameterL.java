package cz.mg.language.entities.logic.mg.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.MgNamedR;


public class MgParameterL extends MgPartL implements MgNamedR {
    @Value
    private final ReadableText name;

    @Link
    private final MgUsageL target;

    public MgParameterL(ReadableText name, MgUsageL target) {
        this.name = name;
        this.target = target;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgUsageL getTarget() {
        return target;
    }
}
