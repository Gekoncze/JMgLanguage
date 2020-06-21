package cz.mg.language.entities.mg.runtime.other;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.clazzes.MgClazz;


public class MgParameter extends MgOther implements Named {
    @Value
    private final ReadableText name;

    @Link
    private final MgClazz type;

    public MgParameter(ReadableText name, MgClazz type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgClazz getType() {
        return type;
    }
}
