package cz.mg.language.entities.mg.runtime.other;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;


public class MgStamp extends MgOther implements Named {
    @Value
    private final ReadableText name;

    public MgStamp(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
