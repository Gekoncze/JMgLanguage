package cz.mg.language.entities.logic.mg.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgNamedL;


public class MgNamedPartL extends MgPartL implements MgNamedL {
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
