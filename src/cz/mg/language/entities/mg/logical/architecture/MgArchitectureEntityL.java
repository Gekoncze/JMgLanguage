package cz.mg.language.entities.mg.logical.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;


public abstract class MgArchitectureEntityL extends MgLogicalEntity implements Named {
    @Value
    private final ReadableText name;

    public MgArchitectureEntityL(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
