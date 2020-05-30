package cz.mg.language.entities.mg.logical.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;


public class MgNamedPartL extends MgPartL implements Named {
    @Value
    private final ReadableText name;

    public MgNamedPartL(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
