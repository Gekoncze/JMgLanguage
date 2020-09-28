package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.MgNamedObject;


public abstract class MgArchitecture implements MgNamedObject {
    @Value
    private final ReadableText name;

    public MgArchitecture(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
