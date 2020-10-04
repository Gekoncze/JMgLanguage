package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;
import cz.mg.language.entities.mg.runtime.MgNamedObject;


public class MgParameter extends MgPart implements MgNamedObject {
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
