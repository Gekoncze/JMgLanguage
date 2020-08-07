package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;


public class MgParameter extends MgPart implements Named {
    @Value
    private final ReadableText name;

    @Link
    private final MgClass type;

    public MgParameter(ReadableText name, MgClass type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgClass getType() {
        return type;
    }
}
