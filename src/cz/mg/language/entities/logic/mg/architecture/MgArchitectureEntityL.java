package cz.mg.language.entities.logic.mg.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgEntityL;
import cz.mg.language.entities.runtime.mg.MgNamedR;


public abstract class MgArchitectureEntityL extends MgEntityL implements MgNamedR {
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
