package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public abstract class MgArchitectureObject extends MgObject implements Named {
    @Value
    private final ReadableText name;

    public MgArchitectureObject(MgType type, ReadableText name) {
        super(type);
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
