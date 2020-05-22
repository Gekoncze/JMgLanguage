package cz.mg.language.entities.runtime.mg.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.MgNamedR;


public class MgStampR extends MgComponentR implements MgNamedR {
    public static final MgStampR PUBLIC = new MgStampR(new ReadonlyText("public"));
    public static final MgStampR PRIVATE = new MgStampR(new ReadonlyText("private"));

    @Value
    private final ReadableText name;

    public MgStampR(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
