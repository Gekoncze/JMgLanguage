package cz.mg.language.entities.mg.logical.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;


public class MgParameterL extends MgPartL implements Named {
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
